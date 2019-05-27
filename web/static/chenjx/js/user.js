$(document).ready(function () {
    $("#btn_login").click(function () {
        var data = {
            userId: $("#username").val(),
            password: $("#password").val(),
            keeplogin: $("[name='remember']:checked").val()
        };

        $.ajax({
            url:"/eExpense_war_exploded/user/login",
            type:"post",
            data:JSON.stringify(data),
            contentType:"application/json;charset=UTF-8",
            success: function(data){
                if(data.state === 0){
                    alert(data.info);
                    init();
                }else{
                    alert(data.info + "  [" + data.state + "]");
                }
            },
            error:function () {
                alert("Ajax error.")
            }
        });
    });

    $("#header_logout").click(function () {
        logout();
    });
    $("#sidebar_left_logout").click(function () {
        logout();
    });

    function logout() {
        $.getJSON("/eExpense_war_exploded/user/logout",function (data) {
            loginVerify(data);
            if(data.state === 0){
                alert(data.info);
                $("#modal_login").show();
            }else{
                alert(data.info + "  [" + data.state + "]");
            }
        });
    }
});