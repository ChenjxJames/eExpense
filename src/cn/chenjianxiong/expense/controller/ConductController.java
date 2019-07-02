package cn.chenjianxiong.expense.controller;

import cn.chenjianxiong.expense.common.Const;
import cn.chenjianxiong.expense.entity.ConductRecord;
import cn.chenjianxiong.expense.entity.ExpenseAccount;
import cn.chenjianxiong.expense.entity.User;
import cn.chenjianxiong.expense.service.ExpenseService;
import cn.chenjianxiong.expense.service.UserService;
import cn.chenjianxiong.expense.vo.Result;
import cn.chenjianxiong.expense.service.ConductService;
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
 * @date ：Created in 2019/5/29 16:19
 * @description：审核管理
 * @modified By：
 * @version: 1.0$
 */

@RequestMapping(value = "/conduct")
@Controller
public class ConductController {
    private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    private ConductService conductService = ctx.getBean(ConductService.class);
    private ExpenseService expenseService = ctx.getBean(ExpenseService.class);
    private UserService userService = ctx.getBean(UserService.class);

    @RequestMapping(value = "/byExpenseAccount/{id}", method = RequestMethod.POST)
    @ResponseBody
    public List<ConductRecord> add(@PathVariable("id") Integer expenseAccountId) {
        List<ConductRecord> conductRecordList = null;
        try{
            conductRecordList = conductService.findConductByExpenseAccount(expenseAccountId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conductRecordList;
    }

    //通过报销单
    @RequestMapping(value = "/pass/{id}", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Result pass(@PathVariable("id") Integer expenseAccountId, HttpSession session) {
        Result result = new Result(-40, "报销单操作失败。");
        try{
            User user = (User) session.getAttribute("USER_LOGIN");
            ExpenseAccount expenseAccount = expenseService.findExpenseAccountById(expenseAccountId);
            User ceo = userService.findCEO();
            if(user.getId().equals(expenseAccount.getConductor())){
                if(expenseAccount.getPrice() > 5000 && !ceo.getId().equals(expenseAccount.getConductor())){
                    //如果报销单值大于5000且此次请求非CEO发起，将处理人设置为CEO（总经理）
                    expenseAccount.setConductor(ceo.getId());
                    expenseAccount.setState(Const.STATE_CHECK_PENDING_WITH_CEO);
                }else{
                    expenseAccount.setConductor("");
                    expenseAccount.setState(Const.STATE_PASS);
                }
                expenseService.updateExpenseAccount(expenseAccount);

                ConductRecord conductRecord = new ConductRecord();
                conductRecord.setExpenseAccountId(expenseAccountId);
                conductRecord.setInfo("无");
                conductRecord.setConductor(user.getId());
                conductRecord.setConductTime(new Date());
                conductRecord.setConductClass("通过");
                conductRecord.setConductResult("成功");
                conductService.conduct(conductRecord);

                result.setState(0);
                result.setInfo("报销单操作成功");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //拒绝报销单
    @RequestMapping(value = "/fail/{id}", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Result fail(@PathVariable("id") Integer expenseAccountId, HttpSession session) {
        Result result = new Result(-40, "报销单操作失败。");
        try{
            User user = (User) session.getAttribute("USER_LOGIN");
            ExpenseAccount expenseAccount = expenseService.findExpenseAccountById(expenseAccountId);
            if(user.getId().equals(expenseAccount.getConductor())){
                expenseAccount.setConductor("");
                expenseAccount.setState(Const.STATE_FAIL);
                expenseService.updateExpenseAccount(expenseAccount);

                ConductRecord conductRecord = new ConductRecord();
                conductRecord.setConductor(user.getId());
                conductRecord.setConductTime(new Date());
                conductRecord.setConductClass("拒绝");
                conductRecord.setConductResult("成功");
                conductRecord.setExpenseAccountId(expenseAccountId);
                conductRecord.setInfo("无");
                conductService.conduct(conductRecord);

                result.setState(0);
                result.setInfo("报销单操作成功");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //驳回报销单，让员工修改
    @RequestMapping(value = "/sendBack/{id}", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Result sendBack(@PathVariable("id") Integer expenseAccountId, HttpSession session) {
        Result result = new Result(-40, "报销单操作失败。");
        try{
            User user = (User) session.getAttribute("USER_LOGIN");
            ExpenseAccount expenseAccount = expenseService.findExpenseAccountById(expenseAccountId);
            if(user.getId().equals(expenseAccount.getConductor())){
                expenseAccount.setConductor(expenseAccount.getCreator());
                expenseAccount.setState(Const.STATE_SEND_BACK);
                expenseService.updateExpenseAccount(expenseAccount);

                ConductRecord conductRecord = new ConductRecord();
                conductRecord.setExpenseAccountId(expenseAccountId);
                conductRecord.setConductor(user.getId());
                conductRecord.setConductTime(new Date());
                conductRecord.setConductClass("驳回修改");
                conductRecord.setConductResult("成功");
                conductRecord.setInfo("无");
                conductService.conduct(conductRecord);

                result.setState(0);
                result.setInfo("报销单操作成功");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
