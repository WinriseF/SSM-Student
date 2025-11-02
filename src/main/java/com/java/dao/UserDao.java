package com.java.dao;

import java.util.List;
import com.java.entity.User;

public interface UserDao {
    void addUser(User user);
    void deleteUser(Integer id);
    void updateUser(User user);
    User getUserById(Integer id);
    List<User> getAllUsers();
}
