// 文件路径: /ssm-student/src/main/java/com/java/service/StudentService.java
package com.java.service;

import com.java.dao.StudentDao;
import com.java.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao; // 注入DAO层

    public void addStudent(Student student) {
        System.out.println(">>> [Service] 准备添加学生: " + student.getName());
        studentDao.addStudent(student);
        System.out.println(">>> [Service] 学生添加成功");
    }

    public void deleteStudent(Integer id) {
        System.out.println(">>> [Service] 准备删除ID为 " + id + " 的学生");
        studentDao.deleteStudentById(id);
        System.out.println(">>> [Service] 学生删除成功");
    }

    public void updateStudent(Student student) {
        System.out.println(">>> [Service] 准备更新学生: " + student.getName());
        studentDao.updateStudent(student);
        System.out.println(">>> [Service] 学生更新成功");
    }

    public Student getStudentById(Integer id) {
        System.out.println(">>> [Service] 准备查询ID为 " + id + " 的学生");
        return studentDao.getStudentById(id);
    }

    public List<Student> getAllStudents() {
        System.out.println(">>> [Service] 准备查询所有学生");
        return studentDao.getAllStudents();
    }
}