$(function () {
    // 页面加引导
    if ($.cookie('model-guanji') == "false" && $.cookie('model-zhanxian') == "false" && $.cookie('model-bujie') == "false") {
        if ($(".weui-tab").length > 0) {
            $(".weui-tab").append('<div class="qiyong" onclick="toFirstIn()"><span class="tixing">你还没有启用助理，快去启用</span><img class="youimg" src="../static/images/youjiantou.png" alt=""><div class="dodod"></div></div>');
            $(".weui-tab__panel").css("padding-bottom", "92px");
        }
    }
});