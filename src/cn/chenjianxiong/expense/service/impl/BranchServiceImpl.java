package cn.chenjianxiong.expense.service.impl;

import cn.chenjianxiong.expense.mapper.BranchMapper;
import cn.chenjianxiong.expense.entity.Branch;
import cn.chenjianxiong.expense.service.BranchService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：James Chen
 * @date ：Created in 2019/5/24
 * @description：部门管理服务
 * @modified By：
 * @version: 1.0$
 */

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    BranchMapper branchMapper;

    @Override
    public Branch findBranchById(String id) {
        return branchMapper.findBranchById(id);
    }

    @Test
    public void findBranchByIdTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BranchService branchService = ctx.getBean(BranchService.class);

        String id = "branch123";
        System.out.println(branchService.findBranchById(id));
    }

    @Override
    public List<Branch> findAllBranch() {
        return branchMapper.findAllBranch();
    }

    @Override
    public boolean updateBranch(Branch branch) {
        return branchMapper.updateBranch(branch) == 1;
    }

    @Override
    public boolean addBranch(Branch branch) {
        return branchMapper.addBranch(branch) == 1;
    }

    @Override
    public boolean delBranch(String id) {
        return branchMapper.delBranch(id) == 1;
    }
}
