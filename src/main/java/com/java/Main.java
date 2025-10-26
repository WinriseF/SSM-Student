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

        Student newStudent = new Student();
        newStudent.setId("1002");
        newStudent.setName("李四");

        studentService.addStudent(newStudent);

        System.out.println("------------------------------");

        studentService.deleteStudent(1002);

        System.out.println("------------------------------");

        String studentInfo = studentService.getStudentInfo();
        System.out.println(studentInfo);
    }
}