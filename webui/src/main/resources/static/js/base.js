$(function(){
    //首页轮播广告
    var timer=null,index=0;
    var len=$(".slide_list li").length;
    $(".slide_list li:not(:first-child)").css({"opacity":0,"z-index":0});
    timer=setInterval(function () {
        if(index==len-1){
            index=0;
        }else{
            index++;
        }
        changeTo(index);
    },3000);
    function changeTo(i){
        $(".slide_list li").eq(i).animate({"opacity":1,"z-index":1},"slow").addClass("active").show().siblings("li:visible").animate({"opacity":0,"z-index":0},"slow").removeClass("active").hide();
        $(".slide_mask_list h2").html($(".slide_list li").eq(i).find("img").attr("alt"));
        $(".indicator a").eq(i).addClass("active").siblings(".active").removeClass("active");
    }

    //搜索框搜索
    $(".form_box").on("click","a",function () {
        if($(this).hasClass("drop_toggle")){
            $(this).next(".drop_down").toggle(100);
        }else{
            $(this).parent().prev(".drop_toggle").children("span").html($(this).html());
            $(this).parent().hide(100);
        }
    });
    $(document).on("click", function () {
        $(".drop_down").hide(100);
    }).on("click",".form_box", function (e) {
        e.stopPropagation();
    });

    //书 章 文章标签切换
    $(".exp_tabs").on("click","a", function () {
        if(!$(this).hasClass("active")){
            var i=$(this).index();
            $(this).addClass("active").siblings(".active").removeClass("active");
            $(".exp_item_con .exp_item_list").eq(i).addClass("show").show().siblings(".show").removeClass("show").hide();
        }
    });

    //图书期刊标签切换
    $(".data_nav").on("click","a",function () {
        if(!$(this).hasClass("active")){
            var i=$(this).index();
            $(this).addClass("active").siblings(".active").removeClass("active");
            $(".data_body .data_body_text").eq(i).addClass("show").show().siblings(".show").removeClass("show").hide();
        }
    });

    //图书详情页参考文献切换
    $(".literature h3 a").click(function () {
        if($(this).hasClass("open")){
            $(this).removeClass("open");
            $(this).parent().parent().next().children(".lit_con_det").removeClass("show");
        }else{
            $(this).addClass("open");
            $(this).parent().parent().next().children(".lit_con_det").addClass("show");
        }
    });

    //footer 选择公司点击事件
    $(".company").on("click","a",function () {
        if($(this).hasClass("company_sel")){
            $(this).next(".company_item").toggle(100);
        }else{
            $(this).parent().prev(".company_sel").children("span").html($(this).html());
            $(this).parent().hide(100);
        }
        $(this).parent().siblings().children(".company_item").hide(100);
    });
    $(document).on("click",function () {
        $(".company_item").hide(100);
    }).on("click",".company", function (e) {
        e.stopPropagation();
    });
});

function doSearch(pageNum) {
    $("#hidPageNum").val(pageNum);
    $("#Search_form").submit();
}
