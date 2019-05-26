package cn.chenjianxiong.expense.service;

import cn.chenjianxiong.expense.po.Branch;

import java.util.List;

public interface BranchService {
    Branch findBranchById(String id);
    List<Branch> findAllBranch();
    boolean updateBranch(Branch branch);
    boolean addBranch(Branch branch);
    boolean delBranch(String id);
}
