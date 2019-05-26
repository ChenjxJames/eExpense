package cn.chenjianxiong.expense.service;

import cn.chenjianxiong.expense.po.ExpenseAccount;
import cn.chenjianxiong.expense.po.ExpenseAccountInfo;

import java.util.List;

public interface ExpenseService {
    boolean addExpenseAccount(ExpenseAccount expenseAccount, List<ExpenseAccountInfo> expenseAccountInfoList);
    boolean addExpenseAccountInfo(ExpenseAccountInfo expenseAccountInfo);
    boolean delExpenseAccount(Integer id);
    boolean delExpenseAccountInfo(Integer id);
    boolean updateExpenseAccount(ExpenseAccount expenseAccount);
    boolean updateExpenseAccountInfo(ExpenseAccountInfo expenseAccountInfo);
    List<ExpenseAccount> findAllExpenseAccount();
    List<ExpenseAccountInfo> findExpenseAccountInfoByExpenseAccountId(Integer id);
    ExpenseAccount findExpenseAccountById(Integer id);
    ExpenseAccountInfo findExpenseAccountInfoById(Integer id);
}
