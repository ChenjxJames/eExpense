package cn.chenjianxiong.expense.test;

import cn.chenjianxiong.expense.po.ExpenseAccount;
import cn.chenjianxiong.expense.service.ExpenseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainConsole {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ExpenseService expenseService = ctx.getBean(ExpenseService.class);
//        ExpenseAccount expenseAccount = new ExpenseAccount();
//        expenseAccount.setReason("出差机票费用");
//        expenseAccount.setCreator("f16011112");
//        expenseAccount.setCreateTime(new Date());
//        expenseAccount.setConductor("f16011109");
//        expenseAccount.setPrice(3000);
//        expenseAccount.setState("待审核");
//        System.out.println(expenseService.addExpenseAccount(expenseAccount));
        List<ExpenseAccount> expenseAccountList = expenseService.findAllExpenseAccount();
        for(ExpenseAccount expenseAccount:expenseAccountList){
            System.out.println(expenseAccount);
        }


//        Branch branch = new Branch();
//        branch.setId("branch123");
//        branch.setName("测试部门");
//        branch.setPlace("上海");
//        BranchService branchService = ctx.getBean(BranchService.class);
//        System.out.println(branchService.addBranch(branch));

//        User user = new User();
//        user.setId("f16011112");
//        user.setName("董昱达");
//        user.setPassword("test123");
//        user.setBranch("branch123");
//        user.setPosition("经理");
//        UserService userService = ctx.getBean(UserService.class);
//        System.out.println(userService.register(user));
    }
}
