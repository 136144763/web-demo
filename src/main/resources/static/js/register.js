function getCode(form) {
    var email = form.find('input[name=email]').val().trim();
    var regex=/^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g;
    if (regex.test(email)) {
        $.post('/api/genCode',
            {email: email, type: 'register'},
            function (response) {
            if (response.success) {
                layer.alert('验证码发送成功，请查收邮件，30分钟内有效');
            } else {
                layer.alert('验证码发送失败:'+response.message);
            }
        }, 'json');
    }else {
        layer.alert('请输入正确的邮箱格式');

    }
}


function formValidateE(form, rules) {
    console.log(form);
    form.validate({
        errorPlacement: function (error, element) {
            error.css("color", "red");
            error.appendTo(element.parent());
        },
        rules: rules,
        submitHandler: function (form) {
            //form.submit();
            $("a").remove(".validate");
            $(form).ajaxSubmit({
                method: 'POST',
                url: '/index/Enterprise/add',
                success: function (data) {
                    if (data.success) {
                        location.href="/register-result";
                    } else {
                        for (var i = 0; i < data.message.length; i++) {
                            console.log(data);
                            console.log(data.message[i].defaultMessage);
                            console.log(data.message[i].objectName);
                            var defaultMessage = data.message[i].defaultMessage;
                            var fieldname = data.message[i].objectName;
                            console.log($("#" + fieldname + ""));
                            $("#" + fieldname + "").parent().append('<a style="color: red" class="validate">' + defaultMessage + '</a>');
                        }
                    }
                },
                error: function (xhr, resp, text) {
                    layer.alert(resp);
                }
            });
        }
    });
}

function formValidateP(form, rules) {
    console.log(form);
    form.validate({
        errorPlacement: function (error, element) {
            error.css("color", "red");
            error.appendTo(element.parent());
        },
        rules: rules,
        submitHandler: function (form) {
            //form.submit();
            $("a").remove(".validate");
            $(form).ajaxSubmit({
                method: 'POST',
                url: '/index/Personal/add',
                success: function (data) {
                    if (data.success) {
                        location.href="/register-result";
                    } else {
                       layer.alert("注册失败！");
                    }
                },
                error: function (xhr, resp, text) {
                    layer.alert(resp);
                }
            });
        }
    });
}

function initForm(validateUrl) {
    var eForm = $('#enterprise-form');
    var pForm = $('#personal-form');

    eForm.find('input[name=getCode]').on('click', function () {
        getCode(eForm);
    });

    pForm.find('input[name=getCode]').on('click', function () {
        getCode(pForm);
    });

    var status = $('input[name=status]').val();

    if(status=='supplier'){
        formValidateE(eForm, enterpriseSupplierRules(status, validateUrl));
        formValidateP(pForm, personalRule(status, validateUrl));
    }
    if(status=='purchaser'){
        formValidateE(eForm, enterprisePurchaserRules(status, validateUrl));
        formValidateP(pForm, personalRule(status, validateUrl));
    }

}

$(document).ready(function () {
    const validateUrl = '/validate/customer';
    initForm(validateUrl);
    // 企业 个人 切换
    $('#type_enterprise').on('click', function () {
        $('#type_personal').removeAttr('checked');
        $('#type_enterprise').attr('checked', 'checked');

        $('.enterprise').show();
        $('.person').hide();

    });
    $('#type_personal').on('click', function () {
        $('#type_enterprise').removeAttr('checked');
        $('#type_personal').attr('checked', 'checked');

        $('.enterprise').hide();
        $('.person').show();

    });
    $('#type_enterprise').attr('checked', 'checked');
});