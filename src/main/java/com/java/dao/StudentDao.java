package com.java.dao;

import com.java.entity.Student;
import java.util.List;

public interface StudentDao {
    /**
     * 新增学生 (写操作)
     */
    int addStudent(Student student);

    /**
     * 根据ID删除学生 (写操作)
     */
    int deleteStudentById(Integer id);

    /**
     * 更新学生信息 (写操作)
     */
    int updateStudent(Student student);

    /**
     * 根据ID查询学生 (读操作)
     */
    Student getStudentById(Integer id);

    /**
     * 查询所有学生 (读操作)
     */
    List<Student> getAllStudents();
}