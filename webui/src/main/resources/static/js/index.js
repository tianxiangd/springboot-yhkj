$(function () {
    getIndexNews();
    //getIndexBook();
});

///首页新闻模块
function getIndexNews() {
    $.ajax({
        url: "/news/queryIndexNews",
        type: "post",
        data: { },
        dataType: "json",
        success: (function (data) {
            if (data.length>0) {
                $("#newsinfo").empty();
                $("#newsinfo").append("<div class='info_img_list clearfix'></div>");
                if (data.length > 0) {
                    $(".info_img_list").append("<div class='info_img fl'><a target='_blank' href='/News/Detail?doi=" + data[0].id + "'><img width='298' height='170' src='" + data[0].FirstImg + "' alt='' /></a> <div class='slide_mask'><div class='slide_mask_bg'></div> <div class='slide_mask_text'> <a target='_blank' href='/News/Detail?id=" + data[0].id + "'>" + data[0].title + "</a></div></div>");
                }
                if (data.length > 1) {
                    $(".info_img_list").append("<div class='info_img fl'><a target='_blank' href='/News/Detail?doi=" + data[1].id + "'><img width='298' height='170' src='" + data[1].FirstImg + "' alt='' /></a> <div class='slide_mask'><div class='slide_mask_bg'></div> <div class='slide_mask_text'> <a target='_blank' href='/News/Detail?id=" + data[1].id + "'>" + data[1].title + "</a></div></div>");
                }
                $("#newsinfo").append("<ul class='info_list'></ul>");
                $.each(data, function (i) {
                    if (i > 1 && i < 5) {
                        $(".info_list").append("<li><span class='fr'>" + data[i].addDate + "</span><i></i><a target='_blank' href='/News/Detail?id=" + data[i].id + "'>" + data[i].title + "</a></li>");
                    }
                })
            }
        }), complete: function (XMLHttpRequest, textStatus) {
            //textStatus的值：success,notmodified,nocontent,error,timeout,abort,parsererror
            $(".loader").removeClass('loader');
        },
    })
}