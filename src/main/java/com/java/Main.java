package com.java;

import com.java.entity.Student;
import com.java.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentService studentService = context.getBean(StudentService.class);

        // 1. 【写操作】新增一个学生
        System.out.println("\n=============== 1. 添加一个新学生 '张三' ================");
        Student newStudent = new Student();
        newStudent.setName("张三");
        newStudent.setGender("男");
        newStudent.setAge(20);
        newStudent.setGrade("2023级软件工程");
        studentService.addStudent(newStudent);

        // 2. 【读操作】查询所有学生，验证添加
        System.out.println("\n=============== 2. 查询所有学生 (应从从库读取) ================");
        List<Student> students = studentService.getAllStudents();
        students.forEach(System.out::println);

        // 模拟主从同步延迟
        System.out.println("\n--- 暂停5秒，模拟主从数据库同步延迟 ---");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 3. 【读操作】再次查询所有学生
        System.out.println("\n=============== 3. 再次查询所有学生 (确认数据已同步) ================");
        List<Student> currentStudents = studentService.getAllStudents();
        currentStudents.forEach(System.out::println);

        // 获取刚插入的学生，用于后续更新和删除
        Student zhangsan = currentStudents.stream()
                .filter(s -> "张三".equals(s.getName()))
                .findFirst()
                .orElse(null);

        if (zhangsan == null) {
            System.out.println("\n错误：未能找到刚添加的学生'张三'，测试终止。请检查数据库主从同步。");
            return;
        }
        System.out.println("\n成功找到学生 '张三'，ID为：" + zhangsan.getId());

        // 4. 【写操作】更新学生信息
        System.out.println("\n=============== 4. 更新 '张三' 的年龄为 21 ================");
        zhangsan.setAge(21);
        studentService.updateStudent(zhangsan);

        // 5. 【读操作】根据ID查询，验证更新
        System.out.println("\n=============== 5. 查询 '张三' 的信息 (应从从库读取) ================");
        Student updatedZhangsan = studentService.getStudentById(zhangsan.getId());
        System.out.println("查询到的更新后信息: " + updatedZhangsan);

        // 6. 【写操作】删除学生
        System.out.println("\n=============== 6. 删除学生 '张三' ================");
        studentService.deleteStudent(zhangsan.getId());

        // 7. 【读操作】最后一次查询所有学生，验证删除
        System.out.println("\n=============== 7. 再次查询所有学生，验证'张三'已被删除 ================");
        List<Student> finalStudents = studentService.getAllStudents();
        if (finalStudents.isEmpty()) {
            System.out.println("所有学生均已删除。");
        } else {
            finalStudents.forEach(System.out::println);
        }
    }
}