package cn.chenjianxiong.expense.mapper;

import cn.chenjianxiong.expense.po.ExpenseAccount;

import java.util.List;

public interface ExpenseAccountMapper {
    int getNextValue();
    ExpenseAccount findExpenseAccountById(Integer id);
    List<ExpenseAccount> findAllExpenseAccount();
    int updateExpenseAccount(ExpenseAccount expenseAccount);
    int addExpenseAccount(ExpenseAccount expenseAccount);
    int delExpenseAccount(Integer id);
}
