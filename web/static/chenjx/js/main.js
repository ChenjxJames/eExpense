$(document).ready(function () {
    init();

    $("#btn_my_expense_account").click(function () {
        $("#left_menu_list").find(".active").removeClass("active");
        $(this).parent().addClass("active");
        $("#content_wrapper").children().css("display", "none");
        $("#content_expense_account_list").css("display", "");

    });

    $("#btn_add_expense_account").click(function () {
        $("#left_menu_list").find(".active").removeClass("active");
        $(this).parent().addClass("active");
        $("#content_wrapper").children().css("display", "none");
        $("#content_add_expense_account").css("display", "");

    });

    $("#btn_user_list").click(function () {
        $("#left_menu_list").find(".active").removeClass("active");
        $(this).parent().addClass("active");
        $("#content_wrapper").children().css("display", "none");
        $("#content_user_list").css("display", "");

        var data = getUsersInfo();
        $("#user_list").empty();
        $.each(data, function (i, item) {
            var html = "<tr class='message-unread'>\n" +
                "           <td class='hidden-xs'>\n" +
                "               <label class='option block mn'>\n" +
                "                   <input type='checkbox' name='mobileos' value='FR'>\n" +
                "                   <span class='checkbox mn'></span>\n" +
                "               </label>\n" +
                "           </td>\n" +
                "           <td>" + item.id + "</td>\n" +
                "           <td>" + item.name + "</td>\n" +
                "           <td class='w600'>" + item.branch + "</td>\n" +
                "           <td class='hidden-xs'><span class='badge badge-warning mr10 fs11'>" + item.position + "</span></td>\n" +
                "           <td><a href='#'>编辑</a></td>\n" +
                "       </tr>";
            $("#user_list").append(html);
        });
    });

    $("#btn_add_user").click(function () {
        $("#left_menu_list").find(".active").removeClass("active");
        $(this).parent().addClass("active");
        $("#content_wrapper").children().css("display", "none");
        $("#content_add_user").css("display", "");

        setBranchInfo(getBranchesInfo());
    });

    $("#btn_branch_list").click(function () {
        $("#left_menu_list").find(".active").removeClass("active");
        $(this).parent().addClass("active");
        $("#content_wrapper").children().css("display", "none");
        $("#content_branch_list").css("display", "");

        var data = getBranchesInfo();
        $("#branch_list").empty();
        $.each(data, function (i, item) {
            var html = "<tr class='message-unread'>\n" +
                "           <td class='hidden-xs'>\n" +
                "               <label class='option block mn'>\n" +
                "                   <input type='checkbox' name='mobileos' value='FR'>\n" +
                "                   <span class='checkbox mn'></span>\n" +
                "               </label>\n" +
                "           </td>\n" +
                "           <td>" + item.id + "</td>\n" +
                "           <td>" + item.name + "</td>\n" +
                "           <td>" + item.place + "</td>\n" +
                "           <td><a href='#'>编辑</a></td>\n" +
                "       </tr>";
            $("#branch_list").append(html);
        })
    });

    $("#btn_add_branch").click(function () {
        $("#left_menu_list").find(".active").removeClass("active");
        $(this).parent().addClass("active");
        $("#content_wrapper").children().css("display", "none");
        $("#content_add_branch").css("display", "");
    });

    //添加部门
    $("#btn_add_branch_submit").click(function () {
        $.ajax({
            url: "/eExpense_war_exploded/branch/add",
            type: "post",
            data: JSON.stringify({
                id: $("#branch_id").val(),
                name: $("#branch_name").val(),
                place: $("#branch_address").val()
            }),
            contentType: "application/json;charset=UTF-8",
            success: function (data) {
                if (data.state === 0) {
                    alert(data.info);
                    $("#branch_id").val("");
                    $("#branch_name").val("");
                    $("#branch_address").val("");
                } else {
                    alert(data.info + "  [" + data.state + "]");
                }
            },
            error: function () {
                alert("Ajax error.")
            }
        });
    });

    //添加员工
    $("#btn_add_user_submit").click(function () {
        $.ajax({
            url: "/eExpense_war_exploded/user/register",
            type: "post",
            data: JSON.stringify({
                id: $("#user_id").val(),
                name: $("#user_name").val(),
                branch: $("#user_branch_id").val(),
                position: $("#user_post").val()
            }),
            contentType: "application/json;charset=UTF-8",
            success: function (data) {
                if (data.state === 0) {
                    alert(data.info);
                    $("#user_id").val("");
                    $("#user_name").val("");
                    $("#user_branch_id").val("");
                    $("#user_post").val("");
                } else {
                    alert(data.info + "  [" + data.state + "]");
                }
            },
            error: function () {
                alert("Ajax error.")
            }
        });
    });

    function getUsersInfo() {
        var result;
        $.ajaxSettings.async = false;
        $.getJSON("/eExpense_war_exploded/user/all", function (data) {
            if (data !== null) {
                if (typeof (data.state) === "undefined") {
                    result = data;
                } else {
                    loginVerify(data);
                    alert(data.info + "  [" + data.state + "]");
                }
            } else {
                alert("员工信息获取异常");
            }
        });
        $.ajaxSettings.async = true;
        return result;
    }

});

function getBranchesInfo() {
    var result;
    $.ajaxSettings.async = false;
    $.getJSON("/eExpense_war_exploded/branch/all", function (data) {
        if (data !== null) {
            if (typeof (data.state) === "undefined") {
                result = data;
            } else {
                loginVerify(data);
                alert(data.info + "  [" + data.state + "]");
            }
        } else {
            alert("部门信息获取异常");
        }
    });
    $.ajaxSettings.async = true;
    return result;
}

function setBranchInfo(data) {
    if (data) {
        $("#user_branch_id").empty();
        $.each(data, function (i, item) {
            $("#user_branch_id").append("<option value ='" + item.id + "'>" + item.name + "</option>");
        });
        return true;
    } else {
        return false;
    }
}

function getPersonalInfo() {
    var result;
    $.ajaxSettings.async = false;
    $.getJSON("/eExpense_war_exploded/user/my", function (data) {
        if (data !== null) {
            if (typeof (data.state) === "undefined") {
                result = data;
            } else {
                loginVerify(data);
                alert(data.info + "  [" + data.state + "]");
            }
        } else {
            alert("个人信息获取异常");
        }
    });
    $.ajaxSettings.async = true;
    return result;
}

function setPersonalInfo(data) {
    $("#show_user_id").text(data.id);
    $("#show_name_and_post").text(data.name + "--" + data.position);
}

function init() {
    if (setBranchInfo(getBranchesInfo())) {
        setPersonalInfo(getPersonalInfo());
        $("#modal_login").hide();
    }
}