$(document).ready(function () {
    $("#btn_my_expense_account").click(function () {
        $("#left_menu_list").find(".active").removeClass("active");
        $(this).parent().addClass("active");
        $("#content_wrapper").children().css("display","none");
        $("#content_expense_account_list").css("display","");

    });

    $("#btn_add_expense_account").click(function () {
        $("#left_menu_list").find(".active").removeClass("active");
        $(this).parent().addClass("active");
        $("#content_wrapper").children().css("display","none");
        $("#content_add_expense_account").css("display","");

    });

    $("#btn_user_list").click(function () {
        $("#left_menu_list").find(".active").removeClass("active");
        $(this).parent().addClass("active");
        $("#content_wrapper").children().css("display","none");
        $("#content_user_list").css("display","");
    });

    $("#btn_add_user").click(function () {
        $("#left_menu_list").find(".active").removeClass("active");
        $(this).parent().addClass("active");
        $("#content_wrapper").children().css("display","none");
        $("#content_add_user").css("display","");
    });

    $("#btn_branch_list").click(function () {
        $("#left_menu_list").find(".active").removeClass("active");
        $(this).parent().addClass("active");
        $("#content_wrapper").children().css("display","none");
        $("#content_branch_list").css("display","");
    });

    $("#btn_add_branch").click(function () {
        $("#left_menu_list").find(".active").removeClass("active");
        $(this).parent().addClass("active");
        $("#content_wrapper").children().css("display","none");
        $("#content_add_branch").css("display","");
    });

});