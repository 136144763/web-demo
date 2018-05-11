/*鼠标移过，左右按钮显示*/
function loginAction() {
    var loginStatus = $("#loginJudge").text();
    if (loginStatus == "exist") {
        $.ajax({
            type: 'POST',
            url: '/login/exit',
            data: loginStatus,
            dataType: 'json',
            success: function (data) {
                layer.alert(data.success, function () {
                    window.location.reload();
                });
                // alert(data.success);
                // window.location.reload();
            },
            error: function (data) {
                alert(data);
            }
        });
    }
}
jQuery(".banner").hover(function () {
    jQuery(this).find(".prev,.next").stop(true, true).fadeTo("show", 0.2)
}, function () {
    jQuery(this).find(".prev,.next").fadeOut()
});
/*SuperSlide图片切换*/
jQuery(".banner").slide({mainCell: ".pic", effect: "fold", autoPlay: true, delayTime: 300, trigger: "click"});
$('.pagelist li').css('width', '60px');


function pager(curr, q) {
    console.log(curr, q);
    var data = {
        page: curr || 1, //向服务端传的参数，此处只是演示
        // goodsName: encodeURI(q) || ''
        goodsName: q || ''
    };
    console.log(data);
    $.getJSON('/api/goods.json', data, function (res) {

        var goodsList = $('#goods-list ul').empty();
        $.each(res.content, function (index, goods) {
            //console.log(index, goods);
            // console.log(goods.username);
            if(goods.username!="anonymousUser"){
                var href = '/details/' + goods.id;
                var src =goods.imageUrl;
                var html = '<li> <a href="' + href + '"><img src="' + src + '" alt="" style="width: 250px;height: 250px;"/></a>';
                html += '<div class="info">  <span>￥' + goods.salesPrice + '<del  style="color:#999;font-size: 14px;text-decoration:line-through; margin-left: 12px;">￥' + goods.price + '</del></span> <a  href="' + href + '">' + goods.name + '</a> </div>';
                html += '</li>';
                goodsList.append(html);
            }else {
                var href = '/details/' + goods.id;
                var src =goods.imageUrl;
                var html = '<li> <a href="' + href + '"><img src="' + src + '" alt="" style="width: 250px;height: 250px;"/></a>';
                html += '<div class="info">  <span>￥' + goods.price + '</span> <a  href="' + href + '">' + goods.name + '</a><a href="#">登录显示折扣价</a> </div>';
                html += '</li>';
                goodsList.append(html);
            }
        });
        //显示分页
        laypage({
            cont: 'pager',
            pages: res.totalPages, //通过后台拿到的总页数
            curr: curr || 1, //当前页

            jump: function (obj, first) { //触发分页后的回调
                //console.log(obj, first, res);
                //if (first == undefined) first = false;
                if (!first) {
                    console.log('点击跳页触发函数自身，并传递当前页：obj.curr', obj.curr);
                    pager(obj.curr, q);
                }
            }
        });
    });
}

function pagerFind(curr, q) {
    console.log(curr, q);
    var data = {
        page: curr || 1, //向服务端传的参数，此处只是演示
        goodsName: encodeURI(q) || ''
    };
    console.log(data);
    $.getJSON('/api/goods.json', data, function (res) {

        var goodsList = $('#goods-list ul').empty();
        $.each(res.content, function (index, goods) {
            //console.log(index, goods);
            // console.log(goods.username);
            if(goods.username!="anonymousUser"){
                var href = '/details/' + goods.id;
                var src =goods.imageUrl;
                var html = '<li> <a href="' + href + '"><img src="' + src + '" alt="" style="width: 250px;height: 250px;"/></a>';
                html += '<div class="info">  <span>￥' + goods.salesPrice + '<del  style="color:#999;font-size: 14px;text-decoration:line-through; margin-left: 12px;">￥' + goods.price + '</del></span> <a  href="' + href + '">' + goods.name + '</a> </div>';
                html += '</li>';
                goodsList.append(html);
            }else {
                var href = '/details/' + goods.id;
                var src =goods.imageUrl;
                var html = '<li> <a href="' + href + '"><img src="' + src + '" alt="" style="width: 250px;height: 250px;"/></a>';
                html += '<div class="info">  <span>￥' + goods.price + '</span> <a  href="' + href + '">' + goods.name + '</a><a href="#">登录显示折扣价</a> </div>';
                html += '</li>';
                goodsList.append(html);
            }
        });
        //显示分页
        laypage({
            cont: 'pager',
            pages: res.totalPages, //通过后台拿到的总页数
            curr: curr || 1, //当前页

            jump: function (obj, first) { //触发分页后的回调
                //console.log(obj, first, res);
                //if (first == undefined) first = false;
                if (!first) {
                    console.log('点击跳页触发函数自身，并传递当前页：obj.curr', obj.curr);
                    pager(obj.curr, q);
                }
            }
        });
    });
}

$(document).ready(function () {
    pager();

    $('div.seach div input.seach_tj').bind('click', function () {
        pagerFind(1, $('#seach_text').val());
        console.log('search');
    });
});