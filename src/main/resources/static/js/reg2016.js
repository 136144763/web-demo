/***********依赖代码**************/
function passwordStrength(password) {
    var desc = [];
    desc[0] = "请输入至少6位数密码";
    desc[1] = "密码强度:<b>弱</b>";
    desc[2] = "密码强度:<b>较好</b>";
    desc[3] = "密码强度:<b>中等</b>";
    desc[4] = "密码强度:<b>强</b>";
    desc[5] = "密码强度:<b>非常强</b>";
    var score = 0;
    if (password.match(/[a-z]/))
        score++;
    if (password.match(/[0-9]/))
        score++;
    if (password.match(/[A-Z]/))
        score++;
    if (password.match(/[^a-zA-Z0-9]/))
        score++;
    if (password.length >= 12)
        score++;
    if (password.length < 6)
        score = 0;
    $("#J_passwordDesc").html($.trim(desc[score]));
    $("#J_passwordLevel").attr("class", "pwd-strenth-level pwd-strenth-level-" + score);
}
/****************依赖代码 结束******************************/
var reg2016 = {
    /**
     * 校验每一个输入项是否正确
     * @param  {[type]} item [description]
     * @return {[boolean]}      [返回字段]
     */
    validateItem: function(item, callback) {
        var self = this;
        item = $(item);
        var name = $.trim(item.attr('name'));
        // password 是否可以输入空格问题
        var value = escape($.trim(item.val()));
        var info = "act=check&inputname=" + name + "&inputvalue=" + value;

        if (name == "u_province" || name == "u_city" || name == "u_citym")
            info = "act=check&inputname=proandcity&inputvalue=" + [escape($.trim($("#J_province").val())), escape($.trim($("#J_city").val())), escape($.trim($("#J_citym").val()))].join(',');
        else if (name == "u_company") {
            info += "," + $.trim(escape(this.regForm.find("input[name='u_class']:checked").val()));
        } else if (name == "myquestion") {
            if ($("#J_question").val() == "*我的自定义问题") {
                info = "act=check&inputname=question&inputvalue=" + value;
            } else {
                this.showValidateResult(item, "");
            }
        } else if (name == "u_password2") {
            // 空格问题
            if ($("#J_password").val() == item.val()) {
                this.showValidateResult(item, "");
                callback && callback('');
            } else {
                this.showValidateResult(item, "两次密码输入不相同");
                callback && callback('两次密码输入不相同');
            }
            return;
        }

        // 发送交易校验
        $.ajax({
            type: "POST",
            url: "/reg/default.asp",
            timeout: 10000,
            data: info,
            error: function(request, settings) {
                self.showValidateResult(item, "出错" + request + settings);
                callback && callback("出错" + request + settings);
            },
            success: function(validateMsg) {
                self.showValidateResult(item, validateMsg);
                callback && callback(validateMsg);
            }
        });
    },
    /**
     * 显示辅助提示信心
     * @param  {[dom]} item   [表单元素 input or select等]
     * @param  {[boolean]} status [显示或者隐藏]
     * @param  {[string]} info   [描述信息]
     * @return {[undefined]}        [description]
     */

    displayAssistantInfo: function(item, status, info) {
        var targetAssiInfo = $(item).siblings('.reg-prompt');
        // 自定义信息
        if (info) {
            targetAssiInfo.find('p').html(info);
        }
        // 显示提示信息
        if (status) {
            targetAssiInfo.show();
            // 如果需要显示提示信息 则关闭错误信息提示
            $(item).siblings('.reg-error').hide();
        } else {
            targetAssiInfo.hide();
        }
    },
    /**
     * 显示错误信息
     * @param  {[type]} item [description]
     * @param  {[type]} info [description]
     * @return {[type]}      [description]
     */
    showValidateResult: function(item, info) {
        // console.log('-----------showValidateResult--------');
        var errorItem = item.siblings('.reg-error');
        var successItem = item.siblings('.reg-success');
        if (info == '') {
            errorItem.hide();
            // 显示校验成功
            successItem.css('display', 'inline-block');
        } else {
            errorItem.html(info);
            errorItem.show();
            // 隐藏校验成功
            successItem.hide();
        }
    },
    // 提交校验
    onSubmit: function() {
        if (this.isSubmitting === true) {
            console.warn('当前数据校验还未完毕');
            return;
        }
        this.isSubmitting = true;

        var self = this;
        // 执行校验
        if (!$("#J_agreement").prop('checked')) {
            layer.alert('您还没有同意西部数码服务条款总章');
            this.isSubmitting = false;
            return;
        }
        this.loadingMask = layer.load(2);
        // ???
        this.regForm.find("input[name='act']:hidden").val("");
        // 前端填写的字段合集
        var inputArr = (must_input + "," + nomust_input).split(",");

        // 如果选择的是 *我的自定义问题  则需要校验问题
        if ($("#J_question").val() == '*我的自定义问题') {
            inputArr.push('myquestion');
        }

        this.waitValidateCount = inputArr.length;
        // 当前已校验项统计
        this.validateTotal = 0;
        // 是否全部校验通过
        this.isAllValidatePass = true;
        // 个人用户 跳过公司名称校验
        var isSkip_u_company = false;
        if (this.regForm.find("input[name='u_class']:checked").val() == "个人用户") {
            isSkip_u_company = true;
        }
        // 遍历所有输入框 执行校验
        $.each(inputArr, function(i, n) {
            var name = $.trim(n);
            if (name == 'u_company' && isSkip_u_company) {
                // 直接过
                self.validateTotal++;
                handleSubmit();
                return;
            }
            var item = self.regForm.find("[name='" + name + "']");
            if (item.length) {
                self.validateItem(item, function(result) {
                    self.validateTotal++;
                    if (result != '') {
                        self.isAllValidatePass = false;
                        // console.log('-- ' + name + '---校验失败 ---> ' + result);
                    }
                    handleSubmit();
                });
            } else {
                // 没有找到该字段 直接过
                self.validateTotal++;
                handleSubmit();
            }
        });
        // 检查是否校验完毕 全部正确 则提交表单
        function handleSubmit() {
            // 校验完毕
            if (self.validateTotal == self.waitValidateCount) {
                // 全部校验OK
                if (self.isAllValidatePass) {
                    // ???
                    self.regForm.find("input[name='act']:hidden").val("act");
                    // console.error('校验成功 --- > 执行数据提交');
                    // layer.alert('校验成功 --- > 执行数据提交');
                    self.regForm.submit();
                    // self.isSubmitting = false;
                    // layer.close(self.loadingMask);
                } else {
                    self.isSubmitting = false;
                    layer.close(self.loadingMask);
                }
            }
        }
    },
    // printStatus: function() {
    // console.log('isSubmitting----->' + this.isSubmitting);
    // console.log('waitValidateCount----->' + this.waitValidateCount);
    // console.log('validateTotal----->' + this.validateTotal);
    // console.log('isAllValidatePass----->' + this.isAllValidatePass);
    // },
    // 事件绑定
    regEvent: function() {
        var self = this;
        // 表单输入框 失去焦点 执行校验动作
        this.regForm.on('focus', 'input', function(event) {
            self.displayAssistantInfo(event.target, true);
        }).on('blur', 'input', function(event) {
            self.displayAssistantInfo(event.target, false);
            self.validateItem(event.target);
        });

        // 表单下拉框 事件处理
        this.regForm.on('focus', 'select.reg-area-select', function(event) {
            var name = event.target.name;
            var info;
            if (name == 'u_province') {
                info = '您所在的省份';
            } else if (name == 'u_city') {
                info = '您所在的城市';
            } else {
                info = '您所在的区域';
            }
            self.displayAssistantInfo(event.target, true, info);
        }).on('blur', 'select.reg-area-select', function(event) {
            self.displayAssistantInfo(event.target, false);
            self.validateItem(event.target);
        });

        // 密保问题
        $("#J_question").change(function(event) {
            if ($(this).val() == '*我的自定义问题') {
                $("#J_customQuestionRow").show();
            } else {
                $("#J_customQuestionRow").hide();
            }
        });

        // 用户类型切换
        $("#J_userClass1").add($("#J_userClass2")).change(function(event) {
            var val = $(this).val();
            if (val == '公司用户') {
                $("#J_companyInputRow").show();
            } else {
                $("#J_companyInputRow").hide();
            }
        });

        //对密码添加按键事件用以验证密码强度
        $("#J_password").keyup(function() {
            passwordStrength($(this).val());
        });


        $("#J_submitRegForm").on('click', function() {
            self.onSubmit();
        });
    },
    // 表单初始化
    init: function() {
        // 注册表单容器
        this.regForm = $("#J_regForm");
        // 1. 初始化页面插件
        var province="",city="";
        // if(userform && userform.guo){
        //     // 外国
        //     if(userform.guo!='cn'){
        //         province = '海外';
        //         city=userform.sheng;
        //     }else{
        //         province = userform.sheng;
        //         city=userform.shi;
        //     }
        // }
        $("#J_ssxContainer").ProvinceCity(province, city);
        // 2. 绑定事件
        this.regEvent();
    }
};

reg2016.init();
