package com.java;

import com.java.dao.UserDao;
import com.java.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class JdbcTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = context.getBean("userDaoImpl", UserDao.class);

        // 测试 addUser
        System.out.println("------ 1. 添加用户 ------");
        User newUser = new User();
        newUser.setName("小红");
        newUser.setAge(30);
        userDao.addUser(newUser);
        System.out.println("添加用户小红成功！");

        // 测试 getAllUsers
        System.out.println("\n------ 2. 查询所有用户 ------");
        List<User> users = userDao.getAllUsers();
        users.forEach(System.out::println);

        // 测试 updateUser
        System.out.println("\n------ 3. 更新用户 ------");
        User userToUpdate = users.stream().filter(u -> "小红".equals(u.getName())).findFirst().orElse(null);
        if (userToUpdate != null) {
            userToUpdate.setAge(965);
            userDao.updateUser(userToUpdate);
            System.out.println("更新用户 " + userToUpdate.getName() + " 的年龄为 965");
        }


        // 测试 getUserById
        System.out.println("\n------ 4. 查询单个用户 ------");
        User singleUser = null;
        if (userToUpdate != null) {
            singleUser = userDao.getUserById(userToUpdate.getId());
        }
        System.out.println("查询到的用户: " + singleUser);


        // 测试 deleteUser
        System.out.println("\n------ 5. 删除用户 ------");
        userDao.deleteUser(singleUser.getId());
        System.out.println("删除用户 " + singleUser.getName() + " 成功！");


        // 再次查询所有用户，验证删除
        System.out.println("\n------ 6. 再次查询所有用户 ------");
        List<User> remainingUsers = userDao.getAllUsers();
        remainingUsers.forEach(System.out::println);
    }
}