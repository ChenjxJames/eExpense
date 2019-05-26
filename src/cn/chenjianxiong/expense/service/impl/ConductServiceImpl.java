package cn.chenjianxiong.expense.service.impl;

import cn.chenjianxiong.expense.mapper.ConductRecordMapper;
import cn.chenjianxiong.expense.po.ConductRecord;
import cn.chenjianxiong.expense.service.ConductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：James Chen
 * @date ：Created in 2019/5/24 15:17
 * @description：报销处理（审核）服务
 * @modified By：
 * @version: 1.0$
 */
@Service
public class ConductServiceImpl implements ConductService {
    @Autowired
    ConductRecordMapper conductRecordMapper;

    @Override
    public boolean conduct(ConductRecord conductRecord) {
        return conductRecordMapper.addConductRecord(conductRecord) == 1;
    }
}
