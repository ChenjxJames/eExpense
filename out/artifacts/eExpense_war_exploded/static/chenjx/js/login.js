$(document).ready(function () {
    $("#btn_submit").click(function () {
        var data = {
            userId: $("#username").val(),
            password: $("#password").val(),
            keeplogin: $("[name='remember']:checked").val()
        };

        // x-www-form-urlencoded方式请求，@RequestParam实现数据绑定，适用于普通数据传递
        // $.post("/eExpense_war_exploded/user/login", data, function (data) {
        //     if(data!=null){
        //         alert(data.state + data.info);
        //     }
        // });


        // json方式请求，@RequestBody实现数据绑定，适用于实体类属性数据传递
        $.ajax({
            url:"/eExpense_war_exploded/user/login",
            type:"post",
            data:JSON.stringify(data),
            contentType:"application/json;charset=UTF-8",
            success: function(data){
                if(data!=null){
                    alert(data.state + data.info);
                }
            },
            error:function () {
                alert("Ajax error.")
            }
        });
    });
});