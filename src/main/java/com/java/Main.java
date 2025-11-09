package com.java; // 你的 Main 类所在的包

// 确保导入的包名和你的项目结构一致
import com.java.entity.Student;
import com.java.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        StudentService studentService = (StudentService) context.getBean("studentService");

        System.out.println("------ 1. 测试内存添加学生 ------");
        Student newStudent = new Student();
        newStudent.setId("1002");
        newStudent.setName("李四");
        studentService.addStudent(newStudent); // 这个方法也会被拦截

        System.out.println("\n------ 2. 测试内存删除学生 ------");
        studentService.deleteStudentTemp(1002);

        System.out.println("\n------ 3. 测试内存更新学生 ------");
        Student studentToUpdate = new Student();
        studentToUpdate.setId("1003");
        studentToUpdate.setName("王五");
        studentService.updateStudentTemp(studentToUpdate);
    }
}