package cn.chenjianxiong.expense.service.impl;

import cn.chenjianxiong.expense.entity.ConductRecord;
import cn.chenjianxiong.expense.mapper.ConductRecordMapper;
import cn.chenjianxiong.expense.mapper.ExpenseAccountInfoMapper;
import cn.chenjianxiong.expense.mapper.ExpenseAccountMapper;
import cn.chenjianxiong.expense.entity.ExpenseAccount;
import cn.chenjianxiong.expense.entity.ExpenseAccountInfo;
import cn.chenjianxiong.expense.service.ExpenseService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author ：James Chen
 * @date ：Created in 2019/5/24
 * @description：报销服务
 * @modified By：
 * @version: 1.0$
 */

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    ExpenseAccountMapper expenseAccountMapper;

    @Autowired
    ExpenseAccountInfoMapper expenseAccountInfoMapper;

    @Autowired
    ConductRecordMapper conductRecordMapper;


    @Override
    @Transactional
    public boolean addExpenseAccount(ExpenseAccount expenseAccount, List<ExpenseAccountInfo> expenseAccountInfoList){
        expenseAccount.setId(expenseAccountMapper.getNextValue());
        int count = expenseAccountMapper.addExpenseAccount(expenseAccount);
        for(ExpenseAccountInfo expenseAccountInfo:expenseAccountInfoList){
            expenseAccountInfo.setExpenseAccountId(expenseAccount.getId());
            count += expenseAccountInfoMapper.addExpenseAccountInfo(expenseAccountInfo);
        }
        boolean result = (count == expenseAccountInfoList.size() + 1);
        if(result){
            ConductRecord conductRecord = new ConductRecord();
            conductRecord.setExpenseAccountId(expenseAccount.getId());
            conductRecord.setConductClass("创建");
            conductRecord.setConductor(expenseAccount.getCreator());
            conductRecord.setConductTime(new Date());
            conductRecord.setConductResult("成功");
            conductRecord.setInfo("无");
            conductRecordMapper.addConductRecord(conductRecord);
        }
        return result;
    }

    @Override
    public boolean addExpenseAccountInfo(ExpenseAccountInfo expenseAccountInfo) {
        return expenseAccountInfoMapper.addExpenseAccountInfo(expenseAccountInfo) == 1;
    }

    @Override
    public boolean delExpenseAccount(Integer id) {
        return expenseAccountMapper.delExpenseAccount(id) == 1;
    }

    @Override
    public boolean delExpenseAccountInfo(Integer id) {
        return expenseAccountInfoMapper.delExpenseAccountInfo(id) == 1;
    }

    @Override
    public boolean updateExpenseAccount(ExpenseAccount expenseAccount, List<ExpenseAccountInfo> expenseAccountInfoList) {
        int count = expenseAccountMapper.updateExpenseAccount(expenseAccount);
        for(ExpenseAccountInfo expenseAccountInfo:expenseAccountInfoList){
            expenseAccountInfo.setExpenseAccountId(expenseAccount.getId());
            if(expenseAccountInfo.getId() == -1){
                count += expenseAccountInfoMapper.addExpenseAccountInfo(expenseAccountInfo);
            } else {
                count += expenseAccountInfoMapper.updateExpenseAccountInfo(expenseAccountInfo);
            }
        }
        boolean result = (count == expenseAccountInfoList.size() + 1);
        if (result){
            ConductRecord conductRecord = new ConductRecord();
            conductRecord.setExpenseAccountId(expenseAccount.getId());
            conductRecord.setConductClass("修改");
            conductRecord.setConductor(expenseAccount.getCreator());
            conductRecord.setConductTime(new Date());
            conductRecord.setConductResult("成功");
            conductRecord.setInfo("无");
            conductRecordMapper.addConductRecord(conductRecord);
        }
        return result;
    }

    @Override
    public boolean updateExpenseAccount(ExpenseAccount expenseAccount) {
        return expenseAccountMapper.updateExpenseAccount(expenseAccount) == 1;
    }

    @Override
    public List<ExpenseAccount> findAllExpenseAccount() {
        return expenseAccountMapper.findAllExpenseAccount();
    }

    @Override
    public List<ExpenseAccount> findExpenseAccountByConductor(String id) {
        return expenseAccountMapper.findExpenseAccountByConductorId(id);
    }

    @Override
    public List<ExpenseAccount> findExpenseAccountByCreator(String id) {
        return expenseAccountMapper.findExpenseAccountByCreatorId(id);
    }

    @Override
    public List<ExpenseAccountInfo> findExpenseAccountInfoByExpenseAccountId(Integer id) {
        return expenseAccountInfoMapper.findExpenseAccountInfoByExpenseAccountId(id);
    }

    @Override
    public ExpenseAccount findExpenseAccountById(Integer id) {
        return expenseAccountMapper.findExpenseAccountById(id);
    }

    @Override
    public ExpenseAccountInfo findExpenseAccountInfoById(Integer id) {
        return expenseAccountInfoMapper.findExpenseAccountInfoById(id);
    }

    @Test
    public void testAddExpenseAccount(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ExpenseService expenseService = ctx.getBean(ExpenseService.class);
        ExpenseAccount expenseAccount = new ExpenseAccount();
        expenseAccount.setReason("出差");
        expenseAccount.setCreator("f16011112");
        expenseAccount.setCreateTime(new Date());
        expenseAccount.setConductor("f16011109");
        expenseAccount.setPrice(3000);
        expenseAccount.setState("待审核");

        ExpenseAccountInfo expenseAccountInfo = new ExpenseAccountInfo();
        expenseAccountInfo.setExpenseClass("住宿");
        expenseAccountInfo.setPrice(1000);
        expenseAccountInfo.setInfo("");

        ExpenseAccountInfo expenseAccountInfo2 = new ExpenseAccountInfo();
        expenseAccountInfo2.setExpenseClass("交通");
        expenseAccountInfo2.setPrice(2000);
        expenseAccountInfo2.setInfo("");

        List<ExpenseAccountInfo> expenseAccountInfoList = new ArrayList<ExpenseAccountInfo>();
        expenseAccountInfoList.add(expenseAccountInfo);
        expenseAccountInfoList.add(expenseAccountInfo2);

        System.out.println(expenseService.addExpenseAccount(expenseAccount,expenseAccountInfoList));
    }
}
