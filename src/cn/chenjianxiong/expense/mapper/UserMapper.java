package cn.chenjianxiong.expense.mapper;

import cn.chenjianxiong.expense.po.User;

public interface UserMapper {
    User findUserById(String id);
    int updateUser(User user);
    int addUser(User user);
    int delUser(String id);
}
