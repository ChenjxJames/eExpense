package cn.chenjianxiong.expense.controller;

import cn.chenjianxiong.expense.common.Const;
import cn.chenjianxiong.expense.entity.*;
import cn.chenjianxiong.expense.service.ExpenseService;
import cn.chenjianxiong.expense.service.UserService;
import cn.chenjianxiong.expense.vo.ExpenseAccountTotal;
import cn.chenjianxiong.expense.vo.Result;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
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
    private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    private UserService userService = ctx.getBean(UserService.class);

    private ExpenseService expenseService = ctx.getBean(ExpenseService.class);

    //添加报销单
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody ExpenseAccountTotal expenseAccountTotal, HttpSession session) {
        Result result = new Result(-30, "报销单提交失败。");
        try {
            //添加数据
            User user = (User) session.getAttribute("USER_LOGIN");
            ExpenseAccount expenseAccount = expenseAccountTotal.getExpenseAccount();
            expenseAccount.setCreator(user.getId());
            expenseAccount.setCreateTime(new Date());
            User manager = userService.findManagerByBranch(user.getBranch());
            expenseAccount.setConductor(manager.getId());
            expenseAccount.setState(Const.STATE_CHECK_PENDING);

            //提交报销单
            if (expenseService.addExpenseAccount(expenseAccountTotal.getExpenseAccount(), expenseAccountTotal.getExpenseAccountInfoList())) {
                result.setState(0);
                result.setInfo("报销单提交成功。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(-1);
            result.setInfo("服务器错误，请稍后尝试。");
        }
        return result;
    }

    //我的报销单
    @RequestMapping(value = "/my", method = RequestMethod.POST)
    @ResponseBody
    public List<ExpenseAccount> my(HttpSession session) {
        List<ExpenseAccount> expenseAccountList = null;
        try {
            User user = (User) session.getAttribute("USER_LOGIN");
            expenseAccountList = expenseService.findExpenseAccountByCreator(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenseAccountList;
    }

    //等待我处理的报销单
    @RequestMapping(value = "/myTask", method = RequestMethod.POST)
    @ResponseBody
    public List<ExpenseAccount> myTask(HttpSession session) {
        List<ExpenseAccount> expenseAccountList = null;
        try {
            User user = (User) session.getAttribute("USER_LOGIN");
            expenseAccountList = expenseService.findExpenseAccountByConductor(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenseAccountList;
    }

    //根据id返回报销单信息
    @RequestMapping(value = "/id/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ExpenseAccountTotal getExpenseAccountById(@PathVariable("id") Integer expenseAccountId) {
        ExpenseAccountTotal expenseAccountTotal = null;
        try {
            expenseAccountTotal = new ExpenseAccountTotal();
            expenseAccountTotal.setExpenseAccount(expenseService.findExpenseAccountById(expenseAccountId));
            expenseAccountTotal.setExpenseAccountInfoList(expenseService.findExpenseAccountInfoByExpenseAccountId(expenseAccountId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenseAccountTotal;
    }

    //根据id返回报销单信息
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody ExpenseAccountTotal expenseAccountTotal, HttpSession session) {
        Result result = new Result(-31, "报销单修改失败。");
        try {
            //添加数据
            User user = (User) session.getAttribute("USER_LOGIN");
            ExpenseAccount expenseAccount = expenseAccountTotal.getExpenseAccount();
            User manager = userService.findManagerByBranch(user.getBranch());
            expenseAccount.setConductor(manager.getId());
            expenseAccount.setCreator(user.getId());
            expenseAccount.setState(Const.STATE_CHECK_PENDING);

            //提交报销单
            if (expenseService.updateExpenseAccount(expenseAccountTotal.getExpenseAccount(), expenseAccountTotal.getExpenseAccountInfoList())) {
                result.setState(0);
                result.setInfo("报销单修改成功。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(-1);
            result.setInfo("服务器错误，请稍后尝试。");
        }
        return result;
    }

    //删除报销单的一条明细
    @RequestMapping(value = "/delInfo/{id}", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Result delInfo(@PathVariable("id") Integer expenseAccountInfoId) {
        Result result = new Result(-32, "报销单明细删除失败");
        try {
            ExpenseAccountInfo expenseAccountInfo = expenseService.findExpenseAccountInfoById(expenseAccountInfoId);
            ExpenseAccount expenseAccount = expenseService.findExpenseAccountById(expenseAccountInfo.getExpenseAccountId());
            if(expenseService.delExpenseAccountInfo(expenseAccountInfoId)){
                expenseAccount.setPrice(expenseAccount.getPrice() - expenseAccountInfo.getPrice());
                expenseService.updateExpenseAccount(expenseAccount);
                result.setState(0);
                result.setInfo("报销单明细删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
