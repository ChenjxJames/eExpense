package cn.chenjianxiong.expense.controller;

import cn.chenjianxiong.expense.po.Result;
import cn.chenjianxiong.expense.po.User;
import cn.chenjianxiong.expense.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ：James Chen
 * @date ：Created in 2019/5/24 17:10
 * @description：用户管理
 * @modified By：
 * @version: 1.0$
 */
@RequestMapping(value = "/user")
@Controller
public class UserController {
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result register(@RequestBody User user) {
        Result result = new Result(1, "注册失败。");
        try{
            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            UserService userService = ctx.getBean(UserService.class);
            if (userService.register(user)) {
                result.setState(0);
                result.setInfo("注册成功。");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setState(-1);
            result.setInfo("服务器错误，请稍后尝试。");
        }
        return result;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody Map<String, String> map){
        Result result = new Result(-1, "登录失败，用户名或密码错误。");
        try{
            String userId = map.get("userId");
            String password = map.get("password");
            String keeplogin = map.get("keeplogin");

            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            UserService userService = ctx.getBean(UserService.class);

            if (userService.login(userId, password)) {


                //create session,
                // keep login
                if (Boolean.parseBoolean(keeplogin)) {
                    System.out.println("keeplogin");
                    result.setInfo("登陆成功,保持登陆。");
                    //set session time
                } else {
                    result.setInfo("登陆成功。");
                }

                result.setState(0);

            }
        }catch (Exception e){
            e.printStackTrace();
            result.setState(-1);
            result.setInfo("服务器错误，请稍后尝试。");
        }

        return result;
    }

    @RequestMapping(value = "/setPassword", method = RequestMethod.POST)
    @ResponseBody
    public Result setPassword(@RequestBody Map<String, String> map){
        Result result = new Result(-1, "密码更改失败，旧密码输入错误。");
        try{
            String oldPassword = map.get("oldPassword");
            String newPassword = map.get("newPassword");
            String userId = ""; //通过session获取user id

            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            UserService userService = ctx.getBean(UserService.class);

            if (userService.setPassword(userId, oldPassword, newPassword)) {
                result.setState(0);
                result.setInfo("密码更改成功。");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setState(-1);
            result.setInfo("服务器错误，请稍后尝试。");
        }

        return result;
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){


        //delete session

        return "../login.html";
    }
}
