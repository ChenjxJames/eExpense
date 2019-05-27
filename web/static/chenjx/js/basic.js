
//加在请求的success里，根据后端拦截器返回信息，做出相应操作（如提醒用户登录）
function loginVerify(data) {
    if(data.state === -20){
        $("#modal_login").show();
    }
}