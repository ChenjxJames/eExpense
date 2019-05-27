package cn.chenjianxiong.expense.controller;

import cn.chenjianxiong.expense.entity.Result;
import cn.chenjianxiong.expense.entity.User;
import cn.chenjianxiong.expense.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
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
        Result result = new Result(-22, "注册失败。");
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
    public Result login(@RequestBody Map<String, String> map, HttpSession session){
        Result result = new Result(-21, "登录失败，用户名或密码错误。");
        try{
            String userId = map.get("userId");
            String password = map.get("password");
            String keeplogin = map.get("keeplogin");

            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            UserService userService = ctx.getBean(UserService.class);

            if (userService.login(userId, password)) {
                User user = userService.findUserById(userId);
                session.setAttribute("USER_LOGIN", user);  //create session

                if (Boolean.parseBoolean(keeplogin)) {  // keep login 30 day
                    result.setInfo("登陆成功,保持登陆。");
                    session.setMaxInactiveInterval(30*24*60*60);  //set session time
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
        Result result = new Result(-23, "密码更改失败，旧密码输入错误。");
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

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUser(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = ctx.getBean(UserService.class);
        return userService.findAllUser();
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    @ResponseBody
    public User getPersonalInfo(HttpSession session){
        User resultUser = new User();
        User sessionUser = (User) session.getAttribute("USER_LOGIN");
        resultUser.setId(sessionUser.getId());
        resultUser.setName(sessionUser.getName());
        resultUser.setBranch(sessionUser.getBranch());
        resultUser.setPosition(sessionUser.getPosition());
        return resultUser;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Result logout(HttpSession session){
        session.invalidate(); //delete session
        return new Result(0, "注销成功");
    }
}
