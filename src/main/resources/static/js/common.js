var isVIP = false;
var expiretime = "";
$(function () {
    var openid = $.cookie('openid') || "";
    var userId = $.cookie('userId') || "";
    // todo
    // openid = "opNfewkh_P3y7ya0rlw7FXbwwzrQ";
    // userId = 24;
    var nickname = $.cookie('nickname') || "";
    var phonenumber = $.cookie('phonenumber') || "";
    var headimgurl = $.cookie('headimgurl') || "";
    var firstin = $.cookie('firstin') || "";
    firstin = 'true';
    // debugger
    if (!openid || 'undefined' == openid || "" == openid) {
        if (!openid || !nickname || !phonenumber || !headimgurl || !userId || 'null' == phonenumber || 'undefined' == phonenumber) {
            // if (!openid || !nickname || !headimgurl || !userID) {
            var code = getUrlParam("code");
            if (!code) {
                var fromurl = location.href;
                var url = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxec01266416a98d18&redirect_uri='
                    + fromurl + '&response_type=code&scope=snsapi_base&state=STATE&connect_redirect=1#wechat_redirect';
                location.href = url;
            } else {
                $.ajax({
                    url: "https://ai.yousayido.net/busiManagement/wxgzh/wxUserDetail",
                    type: 'GET',//类型
                    async: false,
                    cache: false,
                    data: { code: code },
                    success: function (data, status) {
                        openid = data.openid;
                        $.cookie('openid', data.openid);
                        $.cookie('phonenumber', data.phonenumber);
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
    if (openid && 'null' != openid && 'undefined' != openid && '' != openid) {
        //第一次进入时，调到引导页面去
        if ("true" != firstin) {
            $.cookie('firstin', true);
            var firstinNet = getVal(openid,"firstin");
            if ("true" != firstinNet) {
                //去获取第一次的
                $.cookie('firstin', true);
                setVal(openid,'firstin', "true", false);
    
                $.cookie('model-guanji', false);
                setVal(openid,'model-guanji', "false", false);
                $.cookie('model-zhanxian', false);
                setVal(openid,'model-zhanxian', "false", false);
                $.cookie('model-bujie', false);
                setVal(openid,'model-bujie', "false", false);
                $.cookie('model-wurao', false);
                setVal(openid,'model-wurao', "false", false);
                var shuiji = Math.floor(Math.random() * 10);
                window.location = "./firstin.html?v=" + shuiji;
                return;
            }
            var model_guanji = $.cookie('model-guanji');
            if (model_guanji != "true" && model_guanji != "false") {
                model_guanji = getVal(openid,"model-guanji");
                $.cookie('model-guanji',model_guanji)
            }

            var model_zhanxian = $.cookie('model-zhanxian');
            if (model_zhanxian != "true" && model_zhanxian != "false") {
                model_zhanxian = getVal(openid,"model-zhanxian");
                $.cookie('model-zhanxian',model_zhanxian)
            }

            var model_bujie = $.cookie('model-bujie');
            if (model_bujie != "true" && model_bujie != "false") {
                model_bujie = getVal(openid,"model-bujie");
                $.cookie('model-bujie',model_bujie)
            }

            var model_wurao = $.cookie('model-wurao');
            if (model_wurao != "true" && model_wurao != "false") {
                modelmodel_wurao_bujie = getVal(openid,"model-wurao");
                $.cookie('model-wurao',model_wurao)
            }
        }

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
    // 不处罚滑动点击
    stopTouchendPropagationAfterScroll();

    // $("body").on("click",".weui-picker-container-visible",function(){
    //     $(".close-select").trigger("click");
    // });
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


function stopTouchendPropagationAfterScroll() {
    var locked = false;

    window.addEventListener('touchmove', function (ev) {
        locked || (locked = true, window.addEventListener('touchend', stopTouchendPropagation, true));
    }, true);

    function stopTouchendPropagation(ev) {
        ev.stopPropagation();
        window.removeEventListener('touchend', stopTouchendPropagation, true);
        locked = false;
    }
}

function toFirstIn() {
    var shuiji = Math.floor(Math.random() * 10);
    window.location = "./firstin.html?v=" + shuiji;
}

function toPhoneType() {
    var shuiji = Math.floor(Math.random() * 10);
    window.location = "./phonetype.html?v=" + shuiji;
}

function setVal(openid,key, value, isAsync) {
    if (true != isAsync) {
        isAsync = false;
    }
    $.ajax({
        url: "https://ai.yousayido.net/busiManagement/xbms/saveCookie.do",
        type: 'GET',//类型
        async: isAsync,
        cache: false,
        data: { "key": key, "value": value, "openid": openid },
        success: function (data, status) {
        },
        //失败/超时
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("请求失败");
        }
    });
}
function getVal(openid,key) {
    var result ;
    $.ajax({
        url: "https://ai.yousayido.net/busiManagement/xbms/getCookie.do",
        type: 'GET',//类型
        async: false,
        cache: false,
        data: { key: key, "openid": openid},
        success: function (data, status) {
            if (data.value) {
                result=  data.value;
            } else {
                result = "";
            }
        },
        //失败/超时
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("请求失败");
        }
    });
    return result;
}