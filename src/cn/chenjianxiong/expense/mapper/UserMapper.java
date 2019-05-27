package cn.chenjianxiong.expense.mapper;

import cn.chenjianxiong.expense.entity.User;

import java.util.List;

public interface UserMapper {
    List<User> findAllUser();
    User findUserById(String id);
    int updateUser(User user);
    int addUser(User user);
    int delUser(String id);
}
