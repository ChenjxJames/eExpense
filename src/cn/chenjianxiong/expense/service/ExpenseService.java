package cn.chenjianxiong.expense.service;

import cn.chenjianxiong.expense.entity.ExpenseAccount;
import cn.chenjianxiong.expense.entity.ExpenseAccountInfo;

import java.util.List;

public interface ExpenseService {
    boolean addExpenseAccount(ExpenseAccount expenseAccount, List<ExpenseAccountInfo> expenseAccountInfoList);
    boolean addExpenseAccountInfo(ExpenseAccountInfo expenseAccountInfo);
    boolean delExpenseAccount(Integer id);
    boolean delExpenseAccountInfo(Integer id);
    boolean updateExpenseAccount(ExpenseAccount expenseAccount, List<ExpenseAccountInfo> expenseAccountInfoList);
    boolean updateExpenseAccount(ExpenseAccount expenseAccount);
    List<ExpenseAccount> findAllExpenseAccount();
    List<ExpenseAccount> findExpenseAccountByConductor(String id);
    List<ExpenseAccount> findExpenseAccountByCreator(String id);
    List<ExpenseAccountInfo> findExpenseAccountInfoByExpenseAccountId(Integer id);
    ExpenseAccount findExpenseAccountById(Integer id);
    ExpenseAccountInfo findExpenseAccountInfoById(Integer id);
}
