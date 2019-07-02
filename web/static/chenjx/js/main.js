$(document).ready(function () {
    eExpenseInit();
    var theExpenseAccount = null;
    var theId = 0;

    $("#btn_wait_expense_account").click(function () {
        $("#left_menu_list").find(".active").removeClass("active");
        $(this).parent().addClass("active");
        $("#content_wrapper").children().css("display", "none");
        $("#content_wait_expense_account_list").css("display", "");

        getWaitList();
    });

    $("#btn_my_expense_account").click(function () {
        $("#left_menu_list").find(".active").removeClass("active");
        $(this).parent().addClass("active");
        $("#content_wrapper").children().css("display", "none");
        $("#content_expense_account_list").css("display", "");

        myExpenseAccountList();
    });

    $("#btn_add_expense_account").click(function () {
        $("#left_menu_list").find(".active").removeClass("active");
        $(this).parent().addClass("active");
        $("#content_wrapper").children().css("display", "none");
        $("#content_add_expense_account").css("display", "");

        $("#ea_btn").children().hide();
        $("#btn_add_expense_submit").show();
    });

    $("#btn_user_list").click(function () {
        $("#left_menu_list").find(".active").removeClass("active");
        $(this).parent().addClass("active");
        $("#content_wrapper").children().css("display", "none");
        $("#content_user_list").css("display", "");

        var data = getJson("/user/all", "员工列表获取异常");
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

        setBranchInfo(getJson("/branch/all"));
    });

    $("#btn_branch_list").click(function () {
        $("#left_menu_list").find(".active").removeClass("active");
        $(this).parent().addClass("active");
        $("#content_wrapper").children().css("display", "none");
        $("#content_branch_list").css("display", "");

        var data = getJson("/branch/all");
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
        var data = postJson("/branch/add", {
            id: $("#branch_id").val(),
            name: $("#branch_name").val(),
            place: $("#branch_address").val()
        }, "");
        if (data.state === 0) {
            alert(data.info);
            $("#branch_id").val("");
            $("#branch_name").val("");
            $("#branch_address").val("");
        } else {
            alert(data.info + "  [" + data.state + "]");
        }
    });

    //添加员工
    $("#btn_add_user_submit").click(function () {
        var data = postJson("/user/register", {
            id: $("#user_id").val(),
            name: $("#user_name").val(),
            branch: $("#user_branch_id").val(),
            position: $("#user_post").val()
        }, "");
        if (data.state === 0) {
            alert(data.info);
            $("#user_id").val("");
            $("#user_name").val("");
            $("#user_branch_id").val("");
            $("#user_post").val("");
        } else {
            alert(data.info + "  [" + data.state + "]");
        }
    });

    //新增报销单请求
    $("#btn_add_expense_submit").click(function () {
        var data = postJson("/expense/add", getAddExpenseAccount(), "");
        if (data.state === 0) {
            alert(data.info);
            removeAddExpenseAccountForm();
        } else {
            alert(data.info + "  [" + data.state + "]");
        }
    });

    //修改报销单请求
    $("#btn_update_expense_submit").click(function () {
        var data = postJson("/expense/update", getAddExpenseAccount(), "");
        if (data.state === 0) {
            alert(data.info);
            removeAddExpenseAccountForm();
            $("#content_wrapper").children().css("display", "none");
            $("#content_expense_account_list").css("display", "");
        } else {
            alert(data.info + "  [" + data.state + "]");
        }
    });

    $("#expense_account_list").on("click", "a[title='info']", function () {
        var id = $(this).siblings("p").first().text();
        showExpenseAccount(id);
        $("#btn_update").show();
    });

    $("#wait_expense_account_list").on("click", "a[title='info']", function () {
        var id = $(this).siblings("p").first().text();
        showExpenseAccount(id);
        if (userId === theExpenseAccount.expenseAccount.creator) {
            $("#btn_update").show();
        } else {
            $("#btn_pass").show();
            $("#btn_fail").show();
            $("#btn_send_back").show();
        }
    });

    //批准报销单
    $("#btn_pass").click(function () {
        var data = postJson("/conduct/pass/" + theId, {}, "");
        if (data.state === 0) {
            alert(data.info);
            getWaitList();
            $("#content_wrapper").children().css("display", "none");
            $("#content_wait_expense_account_list").css("display", "");
        } else {
            alert(data.info + "  [" + data.state + "]");
        }
    });

    //拒绝报销单
    $("#btn_fail").click(function () {
        var data = postJson("/conduct/fail/" + theId, {}, "");
        if (data.state === 0) {
            alert(data.info);
            getWaitList();
            $("#content_wrapper").children().css("display", "none");
            $("#content_wait_expense_account_list").css("display", "");
        } else {
            alert(data.info + "  [" + data.state + "]");
        }
    });

    //驳回报销单
    $("#btn_send_back").click(function () {
        var data = postJson("/conduct/sendBack/" + theId, {}, "");
        if (data.state === 0) {
            alert(data.info);
            getWaitList();
            $("#content_wrapper").children().css("display", "none");
            $("#content_wait_expense_account_list").css("display", "");
        } else {
            alert(data.info + "  [" + data.state + "]");
        }
    });

    $("#btn_update").click(function () {
        var data = theExpenseAccount.expenseAccount.reason.split(":");

        $("#e_id").text(theExpenseAccount.expenseAccount.id);
        $("#e_cause").val(data[0]);
        $("#show_e_cause").text(data[0] + ":");
        $("#comment").val(data[1]);
        $("#show_comment").text(data[1]);

        var count = 0;
        $.each(theExpenseAccount.expenseAccountInfoList, function (i, item) {
            count++;
        });

        for (; count > 1; count--) {
            addItem();
        }

        $("#items").children("div").each(function (i, item) {
            $(this).find("p:hidden").first().text(theExpenseAccount.expenseAccountInfoList[i].id);
            $(this).find("[title='item']").first().val(theExpenseAccount.expenseAccountInfoList[i].expenseClass);
            $(this).find("[title='money']").first().val(theExpenseAccount.expenseAccountInfoList[i].price);
            $(this).find("[title='cause']").first().val(theExpenseAccount.expenseAccountInfoList[i].info);
            $(this).find("button").first().attr("onclick", "delClick(" + theExpenseAccount.expenseAccountInfoList[i].id + ");");
        });
        calculateMoney();

        $("#content_wrapper").children().css("display", "none");
        $("#content_add_expense_account").css("display", "");

        $("#ea_btn").children().hide();
        $("#btn_update_expense_submit").show();
    });

    //显示报销单信息信息
    function showExpenseAccount(id) {
        var data = postJson("/expense/id/" + id, {}, "");
        theExpenseAccount = data;
        theId = data.expenseAccount.id;
        $("#show_reason").text(data.expenseAccount.reason);
        $("#show_creator").text(data.expenseAccount.creator);
        $("#show_create_time").text(format(data.expenseAccount.createTime));
        $("#show_price_1").text(data.expenseAccount.price);
        $("#show_price_2").text("总金额：" + data.expenseAccount.price);
        $("#show_state").text(data.expenseAccount.state);

        $("#show_info_list").empty();
        $.each(data.expenseAccountInfoList, function (i, item) {
            var html = " <div class='section row'>\n" +
                "           <div class='col-md-2'>" + item.expenseClass + "</div>\n" +
                "           <div class='col-md-2'>" + item.price + "</div>\n" +
                "           <div class='col-md-6'>" + item.info + "</div>\n" +
                "       </div>";
            $("#show_info_list").append(html);
        });

        $("#show_conduct_list").empty();
        var records = postJson("/conduct/byExpenseAccount/" + id, {}, "");
        $.each(records, function (i, item) {
            var html = "<div class='section row'>\n" +
                "           <div class='col-md-2'>" + format(item.conductTime) + "</div>\n" +
                "           <div class='col-md-2'>" + item.conductor + "</div>\n" +
                "           <div class='col-md-2'>" + item.conductClass + "</div>\n" +
                "           <div class='col-md-2'>" + item.conductResult + "</div>\n" +
                "           <div class='col-md-4'>" + item.info + "</div>\n" +
                "       </div>";
            $("#show_conduct_list").append(html);
        });
        $("#show_btn").children().hide();
        $("#content_wrapper").children().css("display", "none");
        $("#content_expense_account_info").css("display", "");
    }

    //将报销单表单中的信息提取出来
    function getAddExpenseAccount() {
        var expense_account = {
            id: $("#e_id").text(),
            reason: $("#e_cause").val() + ":" + $("#comment").val(),
            price: $("#totalMoney").val()
        };

        var expense_account_items = [];

        $("#items").children("div").each(function (i) {
            var expense_account_item = {
                id: $(this).find("p:hidden").first().text(),
                expenseClass: $(this).find("[title='item']").first().val(),
                price: parseFloat($(this).find("[title='money']").first().val()),
                info: $(this).find("[title='cause']").first().val()
            };
            expense_account_items.push(expense_account_item);
        });

        var data = {
            expenseAccount: expense_account,
            expenseAccountInfoList: expense_account_items
        };
        console.log(data);
        return data;
    }

    //清空添加报销单表单中的数据
    function removeAddExpenseAccountForm() {
        $("#e_cause").val("");
        $("#comment").val("");
        $("#show_e_cause").text("");
        $("#show_comment").text("");
        $("#totalMoney").val("");
        $("#items").children("div").slice(1).remove();
        $("#items").children("div").find("input").val("");
    }

    //请求报销单列表并显示
    function myExpenseAccountList() {
        $("#expense_account_list").empty();
        var data = postJson("/expense/my", {}, "");
        var count = 0;
        $.each(data, function (i, item) {
            count++;
            var html = " <tr class='message-unread'>\n" +
                "           <td class='hidden-xs'>\n" +
                "               <label class='option block mn'>\n" +
                "                   <input type='checkbox' name='mobileos' value='FR'>\n" +
                "                   <span class='checkbox mn'></span>\n" +
                "               </label>\n" +
                "           </td>\n" +
                "           <td class=''>" + item.reason + "</td>\n" +
                "           <td class='hidden-xs'>\n" +
                "               <span class='badge badge-warning mr10 fs11'>" + item.state + "</span>\n" +
                "           </td>\n" +
                "           <td class=''>" + item.creator + "</td>\n" +
                "           <td class=''>￥" + item.price + "</td>\n" +
                "           <td class='text-center fw600'>" + format(item.createTime) + "</td>\n" +
                "           <td><a href='#' title='info'>查看</a><p hidden>" + item.id + "</p></td>\n" +
                "       </tr>";
            $("#expense_account_list").append(html);
        });
        if (count === 0) {
            $("#expense_account_list").append("<tr class='message-unread'><td></td><td></td><td></td><td>空空如也</td></tr>");
        }
    }
});

function delClick(id) {
    console.log(id);
    if (id !== -1) {
        var data = postJson("/expense/delInfo/" + id, {}, "");
        if (data.state === 0) {
            alert(data.info);
        } else {
            alert(data.info + "  [" + data.state + "]");
        }
    }
}

//请求待处理报销单列表并显示
function getWaitList() {
    $("#wait_expense_account_list").empty();
    var data = postJson("/expense/myTask", {}, "");
    var count = 0;
    $.each(data, function (i, item) {
        count++;
        var html = " <tr class='message-unread'>\n" +
            "           <td class='hidden-xs'>\n" +
            "               <label class='option block mn'>\n" +
            "                   <input type='checkbox' name='mobileos' value='FR'>\n" +
            "                   <span class='checkbox mn'></span>\n" +
            "               </label>\n" +
            "           </td>\n" +
            "           <td class=''>" + item.reason + "</td>\n" +
            "           <td class='hidden-xs'>\n" +
            "               <span class='badge badge-warning mr10 fs11'>" + item.state + "</span>\n" +
            "           </td>\n" +
            "           <td class=''>" + item.creator + "</td>\n" +
            "           <td class=''>￥" + item.price + "</td>\n" +
            "           <td class='text-center fw600'>" + format(item.createTime) + "</td>\n" +
            "           <td><a href='#' title='info'>查看</a><p hidden>" + item.id + "</p></td>\n" +
            "       </tr>";
        $("#wait_expense_account_list").append(html);
    });
    if (count !== 0) {
        $("#btn_wait_expense_account_icon_new").show();
    } else {
        $("#btn_wait_expense_account_icon_new").hide();
        $("#wait_expense_account_list").append("<tr class='message-unread'><td></td><td></td><td></td><td>空空如也</td></tr>");
    }
}

function setBranchInfo(data) {
    $("#user_branch_id").empty();
    $.each(data, function (i, item) {
        $("#user_branch_id").append("<option value ='" + item.id + "'>" + item.name + "</option>");
    });
}

function setPersonalInfo(data) {
    if (data) {
        userId = data.id;
        $("#show_user_id").text(data.id);
        $("#show_name_and_post").text(data.name + "--" + data.position);
        return true;
    } else {
        return false;
    }
}

function eExpenseInit() {
    if (setPersonalInfo(getJson("/user/my", "个人信息获取异常"))) {
        $("#modal_login").hide();
        getWaitList();
    }
}

var userId = "";