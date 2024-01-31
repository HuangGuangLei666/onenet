var userId = "";
var openid = "";
var phone = "";
var nickname = "";
var headimgurl = "";
$(function () {
    userId = $.cookie('userId') || "";
    openid = $.cookie('openid') || "";
    phone = $.cookie('phonenumber') || "";
    nickname = $.cookie('nickname') || "";
    headimgurl = $.cookie('headimgurl') || "";

    // phone = "17665305852";
    // userId = 24;
    // nickname = "名称";
    // openid = "opNfewsaXsKFBevTyDRpQzz2Lo0U";
    // isVIP = false;
    // expiretime = null;

    if (headimgurl && "null" != headimgurl && null != headimgurl && "undefined" != headimgurl) {
        $(".headimg").attr("src", headimgurl);
    }
    if (phone && "null" != phone && null != phone && "undefined" != phone) {
        $(".phonenum").html(phone);
        $(".unbind").show();
        $(".bindphone").hide();

        $(".testTing").show();
        $(".testTing").attr("href", "tel:" + phone);
    } else {
        $(".unbind").hide();
        $(".bindphone").show();
    }
    if (nickname && "null" != nickname && null != nickname && "undefined" != nickname) {
        $(".whechtname").html(nickname);
    }
    if (expiretime) {
        expiretime = expiretime.length > 10 ? expiretime.substr(0, 10) : expiretime;
        $(".expiretime").html(expiretime + "到期");
    }
    if (!isVIP) {
        $(".toByVip").show();
        $(".unbind").css("text-align", "center");
    } else {
        $(".huiyuanxufei").show();
    }
    console.info("电话：" + $.cookie('phonenumber'));
    $('.weui-tab').tab({
        defaultIndex: 3,
        activeClass: 'weui-bar__item_on',
        onToggle: function (index) {
            if (index == 0) {
                var shuiji = Math.floor(Math.random() * 10);
                window.location = "./n-phonelist.html?v=" + shuiji;
            } else if (index == 1) {
                var shuiji = Math.floor(Math.random() * 10);
                window.location = "./phonetype.html?v=" + shuiji;
            } else if (index == 2) {
                var shuiji = Math.floor(Math.random() * 10);
                window.location = "./n-status.html?v=" + shuiji;
            } else if (index == 3) {
                
            }
        }
    })

    $('body').on('click', '.theme-wrapper', function (event) {
        if (!phone || 'null' == phone || 'undefined' == phone) {
            $.alert("请选绑定手机号", "提示", function () {
                var shuiji = Math.floor(Math.random() * 10);
                window.location = "./login.html?v=" + shuiji;
                return;
            });
        } else {
            var isCheck = true;
            if ($(this).hasClass("selected")) {
                $(this).removeClass("selected");
                $(this).find(".voiceselect").eq(0).remove();
                isCheck = false;
            } else {
                var tmp = $('.selected');
                for (var i = 0; i < tmp.length; i++) {
                    $(tmp[i]).find(".voiceselect").eq(0).remove();
                    $(tmp[i]).removeClass('selected');
                }
                $('.theme-wrapper').removeClass('selected');
                $(this).addClass('selected');
                $(this).append('<img src="../static/images/choosed.png" class="voiceselect" alt="">');
                isCheck = true;
            }
            var data = $(this).attr("data");
            $.ajax({
                url: "https://ai.yousayido.net/busiManagement/xbms/myStatusChange.do",
                // dataType: 'json',//数据类型
                type: 'GET', //类型
                data: { "id": data, "userId": userID, "isCheck": isCheck },
                success: function (data, status) {
                    // debugger
                    if (data.retCode != 0) {
                        $.toast(data.retDesc, "cancel");
                    } else {
                        $.toast(data.retDesc);
                    }
                },
                //失败/超时
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    if (textStatus === 'timeout') {
                        alert('請求超時');
                    }
                }
            });
        }
    });
    $(".pay_price_one").click(function () {
        $(".pay_price_one").removeClass("pay_price_se");
        $(this).addClass("pay_price_se");
        $(".pricenum").html($(this).find(".price_now").text());
    });
    toVisitClick();

    if (openid && 'null' != openid && 'undefined' != openid && ''!=openid) {
        $.ajax({
            url: 'https://ai.yousayido.net/busiManagement/xbms/isAgentQry.do',
            type: 'get',
            data: { "userId": userId },
            cache: false,
            contentType: false,
            success: function (data, status) {
                if (data) {
                    if (data.identity != null) {
                        $(".agentsale").show();
                    }
                }
            },
            error: function (returndata) {
                $.toast(returndata, "cancel");
            }
        });
    }
})

$(function () {
    $("#username").html($.cookie('nickname'));
    $("#phone").html($.cookie('phonenumber'));
    $("#headimgurl").attr("src", $.cookie('headimgurl'));
});

function toHelp() {
    var shuiji = Math.floor(Math.random() * 10);
    window.location = "./help.html?v=" + shuiji;
}
function toKeFu() {
    var shuiji = Math.floor(Math.random() * 10);
    window.location = "./n-onlinewenda.html?v=" + shuiji;
}

function toSetting() {
    var shuiji = Math.floor(Math.random() * 10);
    window.location = "./setting.html?v=" + shuiji;
}

function toCallList() {
    var shuiji = Math.floor(Math.random() * 10);
    window.location = "./calllist.html?v=" + shuiji;
}

function toPhoneList() {
    // if (false) {
    if (!phone || 'null' == phone || 'undefined' == phone) {
        $.alert("请选绑定手机号", "提示", function () {
            var shuiji = Math.floor(Math.random() * 10);
            window.location = "./login.html?v=" + shuiji;
            return;
        });
    } else {
        var shuiji = Math.floor(Math.random() * 10);
        window.location = "./phonelist.html?v=" + shuiji;
    }
}

function toPhoneType() {
    if (false) {
        // if (!phone || 'null' == phone || 'undefined' == phone) {
        $.alert("请选绑定手机号", "提示", function () {
            var shuiji = Math.floor(Math.random() * 10);
            window.location = "./login.html?v=" + shuiji;
            return;
        });
    } else {
        var shuiji = Math.floor(Math.random() * 10);
        window.location = "./phonetype.html?v=" + shuiji;
    }
}

function toBlackList() {
    var shuiji = Math.floor(Math.random() * 10);
    window.location = "./blacklist.html?v=" + shuiji;
}

function toGroup() {
    // if (false) {
    if (!phone || 'null' == phone || 'undefined' == phone) {
        $.alert("请选绑定手机号", "提示", function () {
            var shuiji = Math.floor(Math.random() * 10);
            window.location = "./login.html?v=" + shuiji;
            return;
        });
    } else {
        var shuiji = Math.floor(Math.random() * 10);
        window.location = "./group.html?v=" + shuiji;
    }
}

function toMatching() {
    var shuiji = Math.floor(Math.random() * 10);
    window.location = "./matching.html?v=" + shuiji;
}

function toInviteFriend() {
    var shuiji = Math.floor(Math.random() * 10);
    window.location = "./n-invitefriend.html?v=" + shuiji;
}

function toPayme() {
    // if (!phone || 'null' == phone || 'undefined' == phone) {
    if (false) {
        $.alert("请选绑定手机号", "提示", function () {
            var shuiji = Math.floor(Math.random() * 10);
            window.location = "./login.html?v=" + shuiji;
            return;
        });
    } else {
        window.location = "./n-payme.html";
    }
}

function toPaythem() {
    if (!phone || 'null' == phone || 'undefined' == phone) {
        // if (false) {
        $.alert("请选绑定手机号", "提示", function () {
            var shuiji = Math.floor(Math.random() * 10);
            window.location = "./login.html?v=" + shuiji;
            return;
        });
    } else {
        var shuiji = Math.floor(Math.random() * 10);
        window.location = "./n-paytheir.html?v=" + shuiji;
    }
}

function toMechanism() {
    if (!phone || 'null' == phone || 'undefined' == phone) {
        // if (false) {
        $.alert("请选绑定手机号", "提示", function () {
            var shuiji = Math.floor(Math.random() * 10);
            window.location = "./login.html?v=" + shuiji;
            return;
        });
    } else {
        var shuiji = Math.floor(Math.random() * 10);
        window.location = "./n-mechanismApply.html?v=" + shuiji;
    }
}

function toOpinion() {
    var shuiji = Math.floor(Math.random() * 10);
    window.location = "./n-opinion.html?v=" + shuiji;
}
function toAgentSale() {
    var shuiji = Math.floor(Math.random() * 10);
    window.location = "./n-agentsale.html?v=" + shuiji;
}
function toDiy() {
    if (!phone || 'null' == phone || 'undefined' == phone) {
        // if (false) {
        $.alert("请选绑定手机号", "提示", function () {
            var shuiji = Math.floor(Math.random() * 10);
            window.location = "./login.html?v=" + shuiji;
            return;
        });
    } else {
        //会员功能
        if (!isVIP) {
            $.alert("您的会员已过期，请立即开通", "提示", function () {
                return toPay();
            });
            return;
        }
        var shuiji = Math.floor(Math.random() * 10);
        window.location = "./n-diyqa.html?v=" + shuiji;
    }
}
function toMonitor() {
    if (!phone || 'null' == phone || 'undefined' == phone) {
        // if (false) {
        $.alert("请选绑定手机号", "提示", function () {
            var shuiji = Math.floor(Math.random() * 10);
            window.location = "./login.html?v=" + shuiji;
            return;
        });
    } else {
        //会员功能
        if (!isVIP) {
            $.alert("您的会员已过期，请立即开通", "提示", function () {
                return toPay();
            });
            return;
        }
        var shuiji = Math.floor(Math.random() * 10);
        window.location = "./n-monitor.html?v=" + shuiji;
    }
}
function bindPhone() {
    var shuiji = Math.floor(Math.random() * 10);
    window.location = "./login.html?v=" + shuiji;
}

function toCode() {
    if (!phone || 'null' == phone || 'undefined' == phone) {
        // if (false) {
        $.alert("请选绑定手机号", "提示", function () {
            var shuiji = Math.floor(Math.random()*10);
            window.location = "./login.html?v="+shuiji;
            return;
        });
    } else {
        var shuiji = Math.floor(Math.random()*10);
        window.location = "./n-usecode.html?v="+shuiji;
    }
}
function toYingDao() {
    var shuiji = Math.floor(Math.random()*10);
    window.location = "./firstin.html?v="+shuiji;
}
function toHistory() {
    if (!phone || 'null' == phone || 'undefined' == phone) {
        // if (false) {
        $.alert("请选绑定手机号", "提示", function () {
            var shuiji = Math.floor(Math.random()*10);
            window.location = "./login.html?v="+shuiji;
            return;
        });
    } else {
        var shuiji = Math.floor(Math.random()*10);
        window.location = "./n-orderhistory.html?v="+shuiji;
    }
}

function closePay() {
    $(".pay_item").animate({ bottom: '-100%' });
    $(".bounced_bg").addClass("display");
}

function toVisitClick() {
    var ifToPay = getUrlParam("topay");
    if (ifToPay == "topay") {
        toPay();
    }
}

function unbindphone() {
    if (openid) {
        $.ajax({
            url: "https://ai.yousayido.net/busiManagement/xbms/relieveUserPhone.do",
            // dataType: 'json',//数据类型
            type: 'GET', //类型
            data: { "openid": openid },
            success: function (data, status) {
                debugger
                if (data.retCode != 0) {
                    $.toast(data.retDesc, "cancel");
                } else {
                    $.cookie('phonenumber', '');
                    location.reload();
                }
            },
            //失败/超时
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (textStatus === 'timeout') {
                    alert('請求超時');
                }
            }
        });
    }
}