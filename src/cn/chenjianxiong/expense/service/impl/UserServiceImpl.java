package cn.chenjianxiong.expense.service.impl;

import cn.chenjianxiong.expense.mapper.UserMapper;
import cn.chenjianxiong.expense.entity.User;
import cn.chenjianxiong.expense.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：James Chen
 * @date ：Created in 2019/5/23
 * @description：用户服务
 * @modified By：
 * @version: 1.0$
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean register(User user) {
        return userMapper.addUser(user) == 1;
    }

    @Override
    public boolean login(String id, String password) {
        boolean result = false;
        User user = userMapper.findUserById(id);
        if (user != null && password.equals(user.getPassword())) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean setPassword(String id, String oldPassword, String newPassword) {
        boolean result = false;
        User user = userMapper.findUserById(id);
        if (user != null && oldPassword.equals(user.getPassword())) {
            user.setPassword(newPassword);
            if (userMapper.updateUser(user) > 0) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public List<User> findAllUser() {
        List<User> userList = userMapper.findAllUser();
        for (User user : userList) {
            user.setPassword("");
        }
        return userList;
    }

    @Override
    public User findUserById(String id) {
        return userMapper.findUserById(id);
    }
}
