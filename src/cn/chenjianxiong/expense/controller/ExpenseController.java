package cn.chenjianxiong.expense.controller;

import cn.chenjianxiong.expense.entity.ExpenseAccount;
import cn.chenjianxiong.expense.entity.ExpenseAccountInfo;
import cn.chenjianxiong.expense.entity.Result;
import cn.chenjianxiong.expense.service.ExpenseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ：James Chen
 * @date ：Created in 2019/5/27 14:41
 * @description：报销管理
 * @modified By：
 * @version: 1.0$
 */
@RequestMapping(value = "/expense")
@Controller
public class ExpenseController {

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody ExpenseAccount expenseAccount, @RequestBody List<ExpenseAccountInfo> expenseAccountInfoList){
        Result result = new Result(-1, "报销单提交失败。");
        try{
            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            ExpenseService expenseService = ctx.getBean(ExpenseService.class);

            if (expenseService.addExpenseAccount(expenseAccount, expenseAccountInfoList)) {
                result.setState(0);
                result.setInfo("报销单提交成功。");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setState(-1);
            result.setInfo("服务器错误，请稍后尝试。");
        }
        return result;
    }

}
