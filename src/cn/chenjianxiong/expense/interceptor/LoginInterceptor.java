package cn.chenjianxiong.expense.interceptor;

import cn.chenjianxiong.expense.vo.Result;
import cn.chenjianxiong.expense.entity.User;
import cn.chenjianxiong.expense.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @author ：James Chen
 * @date ：Created in 2019/5/26 23:10
 * @description：登陆验证
 * @modified By：
 * @version: 1.0$
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri=request.getRequestURI();
        if(uri.contains("/login")) {//说明访问/login.jsp 或提交/login处理器处理， 则继续往下执行
            return true; // 继续往下执行
        }
        //这里说明访问与登录无关的其他资源：判断session：USER_LOGIN
        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("USER_LOGIN");
        if(user != null) {//没登录，或过期了
            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            UserService userService = ctx.getBean(UserService.class);
            if(userService.login(user.getId(), user.getPassword())) {
                return true; //session登录成功
            }
        }
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        Result result = new Result(-20, "请登录。");
        String json = JSON.toJSONString(result);
        writer.print(json);
        writer.close();
        response.flushBuffer();
        return false;  //session登录失败
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
