package cn.chenjianxiong.expense.mapper;

import cn.chenjianxiong.expense.po.ExpenseAccountInfo;

import java.util.List;

public interface ExpenseAccountInfoMapper {
    ExpenseAccountInfo findExpenseAccountInfoById(Integer id);
    List<ExpenseAccountInfo> findExpenseAccountInfoByExpenseAccountId(Integer id);
    int updateExpenseAccountInfo(ExpenseAccountInfo expenseAccountInfo);
    int addExpenseAccountInfo(ExpenseAccountInfo expenseAccountInfo);
    int delExpenseAccountInfo(Integer id);
}
