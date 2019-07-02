package cn.chenjianxiong.expense.service;

import cn.chenjianxiong.expense.entity.User;

import java.util.List;

public interface UserService {
    boolean register(User user);
    boolean login(String id, String password);
    boolean setPassword(String id, String oldPassword, String newPassword);
    List<User> findAllUser();
    User findUserById(String id);
    User findManagerByBranch(String id);
    User findCEO();
}
