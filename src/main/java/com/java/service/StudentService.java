package com.java.service;

import com.java.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private Student student;

    public String getStudentInfo() {

        student.setId("1001");
        student.setName("张三");
        student.setGender("男");
        student.setAge(20);
        student.setGrade("2023 级计算机班");

        return "学号 " + student.getId() + "：" + student.getName() +
                "（" + student.getGender() + "，" + student.getAge() +
                " 岁，" + student.getGrade() + "）";
    }
}