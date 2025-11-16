package com.java;

import com.java.dao.UserDao;
import com.java.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ReadWriteSplittingTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = context.getBean(UserDao.class);

        // 执行写操作
        System.out.println("------ 1. 开始执行写操作 (addUser) ------");
        User newUser = new User();
        newUser.setName("MasterWriteUser");
        newUser.setAge(30);
        userDao.addUser(newUser);
        System.out.println("操作完成。请检查 master_db.user 表，应包含 'MasterWriteUser'。");

        // 模拟主从同步
        System.out.println("\n------ 2. 模拟主从数据同步 ------");
        System.out.println("在真实环境中，数据库会自动同步。");
        System.out.println("为了测试，请手动在 slave_db.user 表中也插入 'MasterWriteUser' 这条记录。");
        try {
            System.out.println("程序将暂停15秒，请在此期间完成手动数据同步...");
            Thread.sleep(15000); // 暂停15秒等待用户操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 执行读操作
        System.out.println("\n------ 3. 开始执行读操作 (getAllUsers) ------");
        List<User> users = userDao.getAllUsers();
        System.out.println("从从库查询到的用户列表：");
        if (users.isEmpty()) {
            System.out.println("未查询到任何用户，请确认您已手动将数据同步到 slave_db。");
        } else {
            users.forEach(System.out::println);
        }
        System.out.println("操作完成。");
    }
}