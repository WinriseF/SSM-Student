package com.java; // 你的 Main 类所在的包

// 确保导入的包名和你的项目结构一致
import com.java.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // 1. 初始化Spring容器，加载 applicationContext.xml 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 2. 从容器中获取 studentService Bean
        // "studentService" 必须和 XML 文件中 bean 的 id 一致
        StudentService studentService = (StudentService) context.getBean("studentService");

        // 3. 调用方法并打印结果
        String studentInfo = studentService.getStudentInfo();
        System.out.println(studentInfo);
    }
}