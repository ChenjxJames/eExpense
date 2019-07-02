package cn.chenjianxiong.expense.service;

import cn.chenjianxiong.expense.entity.ConductRecord;

import java.util.List;

public interface ConductService {
    boolean conduct(ConductRecord conductRecord);
    List<ConductRecord> findConductByExpenseAccount(Integer id);
}
