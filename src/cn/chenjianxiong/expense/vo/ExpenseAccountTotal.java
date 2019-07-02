package cn.chenjianxiong.expense.vo;

import cn.chenjianxiong.expense.entity.ExpenseAccount;
import cn.chenjianxiong.expense.entity.ExpenseAccountInfo;

import java.util.List;

/**
 * @author ：James Chen
 * @date ：Created in 2019/5/29 16:54
 * @description：报销单实体，用于接收前端数据
 * @modified By：
 * @version: 1.0$
 */
public class ExpenseAccountTotal {
    private ExpenseAccount expenseAccount;
    private List<ExpenseAccountInfo> expenseAccountInfoList;

    public ExpenseAccount getExpenseAccount() {
        return expenseAccount;
    }

    public void setExpenseAccount(ExpenseAccount expenseAccount) {
        this.expenseAccount = expenseAccount;
    }

    public List<ExpenseAccountInfo> getExpenseAccountInfoList() {
        return expenseAccountInfoList;
    }

    public void setExpenseAccountInfoList(List<ExpenseAccountInfo> expenseAccountInfoList) {
        this.expenseAccountInfoList = expenseAccountInfoList;
    }

    @Override
    public String toString() {
        return "ExpenseAccountTotal{" +
                "expenseAccount=" + expenseAccount +
                ", expenseAccountInfoList=" + expenseAccountInfoList +
                '}';
    }
}
