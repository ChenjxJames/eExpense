package cn.chenjianxiong.expense.mapper;

import cn.chenjianxiong.expense.po.Branch;

import java.util.List;

public interface BranchMapper {
    Branch findBranchById(String id);
    List<Branch> findAllBranch();
    int updateBranch(Branch branch);
    int addBranch(Branch branch);
    int delBranch(String id);
}
