package com.java.service;

import com.java.entity.User;

public interface UserService {
    User getUserById(Integer id);
    void addUser(User user);
}