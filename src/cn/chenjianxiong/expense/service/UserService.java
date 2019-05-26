package cn.chenjianxiong.expense.service;

import cn.chenjianxiong.expense.po.User;

public interface UserService {
    boolean register(User user);
    boolean login(String id, String password);
    boolean setPassword(String id, String oldPassword, String newPassword);
}
