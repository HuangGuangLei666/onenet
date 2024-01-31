var isVIP = false;
var expiretime = "";
$(function () {
    var openid = $.cookie('openid') || "";
    var userId = $.cookie('userId') || "";
    // openid = "opNfewkh_P3y7ya0rlw7FXbwwzrQ";
    // userId = 24;
    var nickname = $.cookie('nickname') || "";
    var phonenumber = $.cookie('phonenumber') || "";
    var headimgurl = $.cookie('headimgurl') || "";
    var firstin = $.cookie('firstin')||"";
    //第一次进入时，调到引导页面去
    if ("true" != firstin) {
        $.cookie('firstin',true);
        var shuiji = Math.floor(Math.random()*10);
        window.location = "./firstin.html?v="+shuiji;
    }
    // debugger
    if (!openid || 'undefined' == openid) {
        if (!openid || !nickname || !phonenumber || !headimgurl || !userId || 'null' == phonenumber || 'undefined' == phonenumber) {
            // if (!openid || !nickname || !headimgurl || !userId) {
            var code = getUrlParam("code");
            if (!code) {
                var fromurl = location.href;
                var url = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxec01266416a98d18&redirect_uri='
                    + fromurl + '&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect';
                location.href = url;
            } else {
                var parentId = getUrlParam("parentId");
                $.ajax({
                    url: "https://ai.yousayido.net/busiManagement/xbms/wxUserinfo",
                    type: 'GET',//类型
                    async: false,
                    cache: false,
                    data: { 'code': code, 'parentId': parentId},
                    success: function (data, status) {
                        openid = data.openid;
                        $.cookie('openid', data.openid);
                        $.cookie('phonenumber', data.phonenumber);
                        var phonenumber = data.phonenumber;
                        //    if (!phonenumber || 'null'==phonenumber) {
                        //        window.location = "./login.html"
                        //    }
                        $.cookie('nickname', data.nickname);
                        $.cookie('headimgurl', data.headimgurl);
                        $.cookie('userId', data.id);
                    },
                    //失败/超时
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert("请求失败");
                    }
                });
            }
        }
    }
    // 控制会员行为
    if (openid && 'null' != openid && 'undefined' != openid && ''!=openid) {
        $.ajax({
            url: 'https://ai.yousayido.net/busiManagement/xbms/isMembershipQry.do',
            type: 'get',
            data: { "userId": userId },
            async: false,
            cache: false,
            contentType: false,
            success: function (data, status) {
                if (data) {
                    if (data.isMembership != null && (data.isMembership == 1 || data.isMembership == 2)) {
                        isVIP = true;
                        expiretime = data.expireTime;
                    }
                }
            },
            error: function (returndata) {
                $.toast(returndata, "cancel");
            }
        });
    }
    init();
});
function init() {
    $("body").on("click", ".weui-picker-container-visible", function (event) {
        $("body").off("click", ".weui-picker-container-visible");
        $(".close-select").trigger("click");

        init();
    });
}
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}