package cn.chenjianxiong.expense.service.impl;

import cn.chenjianxiong.expense.mapper.BranchMapper;
import cn.chenjianxiong.expense.po.Branch;
import cn.chenjianxiong.expense.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
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
