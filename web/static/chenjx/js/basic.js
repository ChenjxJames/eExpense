$(document).ready(function () {
    $("#addItemButton").click(function () {
        var item = $("#items").children("div").last();
        item.find("p:hidden").first().text("-1");
        item.find("[title='money']").first().val("0");
        item.last().find("[title='cause']").first().val("");
        item.find("button").first().attr("onclick","delClick(-1);");
        calculateMoney();
    });

    $("#e_cause").blur(function () {
        $("#show_e_cause").text($("#e_cause").val() + ":");
    });

    $("#comment").blur(function () {
        $("#show_comment").text($("#comment").val());
    });
});


//加在请求的success里，根据后端拦截器返回信息，做出相应操作（如提醒用户登录）
function loginVerify(data) {
    if (data.state === -20) {
        $("#modal_login").show();
    }
}

//对于请求返回数据的分类处理
function disposeData(data) {
    if (data !== null) {
        if (typeof (data.state) === "undefined") {
            return data;
        } else if (data.state === -20){
            loginVerify(data);
            alert(data.info + "  [" + data.state + "]");
        } else {
            return data;
        }
    } else {
        alert(errorInfo);
    }
    return null;
}

//get请求函数
function getJson(url, errorInfo) {
    var result = null;
    $.ajaxSettings.async = false;
    $.getJSON(url, function (data) {
        result = disposeData(data);
    });
    $.ajaxSettings.async = true;
    return result;
}

//post请求函数
function postJson(url, data, errorInfo) {
    var result = null;
    $.ajax({
        url: url,
        type: "post",
        data: JSON.stringify(data),
        contentType: "application/json;charset=UTF-8",
        async: false,
        success: function (data) {
            result = disposeData(data);
        },
        error: function () {
            alert("Ajax error.")
        }
    });
    return result;
}

//时间戳转格式化时间
function format(shijianchuo)
{
//shijianchuo是整数，否则要parseInt转换
    var time = new Date(shijianchuo);
    var y = time.getFullYear();
    var m = time.getMonth()+1;
    var d = time.getDate();
    var h = time.getHours();
    var mm = time.getMinutes();
    var s = time.getSeconds();
    return y+'-'+m+'-'+d+' '+h+':'+mm+':'+s;
}
