package cn.chenjianxiong.expense.mapper;

import cn.chenjianxiong.expense.entity.ConductRecord;

import java.util.List;

public interface ConductRecordMapper {
    ConductRecord findConductRecordById(Integer id);
    List<ConductRecord> findConductRecordByExpenseAccountId(Integer id);
    int updateConductRecord(ConductRecord conductRecord);
    int addConductRecord(ConductRecord conductRecord);
    int delConductRecord(Integer id);
}
