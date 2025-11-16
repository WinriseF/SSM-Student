package com.java.service;

import com.java.dao.UserDao;
import com.java.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // 声明为 Service Bean
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao; // 注入 UserDAO

    @Override
    public User getUserById(Integer id) {
        System.out.println(">>> [Service] 准备查询ID为 " + id + " 的用户");
        return userDao.getUserById(id);
    }

    @Override
    public void addUser(User user) {
        System.out.println(">>> [Service] 准备添加用户: " + user.getName());
        userDao.addUser(user);
        System.out.println(">>> [Service] 用户添加成功");
    }
}