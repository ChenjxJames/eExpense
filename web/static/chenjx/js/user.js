$(document).ready(function () {
    $("#btn_login").click(function () {
        var data = {
            userId: $("#username").val(),
            password: $("#password").val(),
            keeplogin: $("[name='remember']:checked").val()
        };

        var result = postJson("/user/login",data,"");
        if(result.state === 0){
            alert(result.info);
            eExpenseInit();
        }else{
            alert(result.info + "  [" + result.state + "]");
        }
    });

    $("#header_logout").click(function () {
        logout();
    });
    $("#sidebar_left_logout").click(function () {
        logout();
    });

    function logout() {
        var data = getJson("/user/logout");
        if(data.state === 0){
            alert(data.info);
            $("#modal_login").show();
        }else{
            alert(data.info + "  [" + data.state + "]");
        }
    }
});