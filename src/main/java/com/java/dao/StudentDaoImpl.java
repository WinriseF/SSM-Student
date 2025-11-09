package com.java.dao;

import com.java.datasource.DataSourceContextHolder;
import com.java.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 将该类注册为Spring Bean，并标识为数据访问层组件
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addStudent(Student student) {
        try {
            DataSourceContextHolder.setDataSourceType("master"); // 写操作，切换到主库
            System.out.println("==> [DAO] 数据源切换至 [master] for addStudent");
            String sql = "INSERT INTO student (name, gender, age, grade) VALUES (?, ?, ?, ?)";
            return jdbcTemplate.update(sql, student.getName(), student.getGender(), student.getAge(), student.getGrade());
        } finally {
            DataSourceContextHolder.clearDataSourceType();
            System.out.println("<== [DAO] 数据源设置已清除");
        }
    }

    @Override
    public int deleteStudentById(Integer id) {
        try {
            DataSourceContextHolder.setDataSourceType("master"); // 写操作，切换到主库
            System.out.println("==> [DAO] 数据源切换至 [master] for deleteStudentById");
            String sql = "DELETE FROM student WHERE id = ?";
            return jdbcTemplate.update(sql, id);
        } finally {
            DataSourceContextHolder.clearDataSourceType();
            System.out.println("<== [DAO] 数据源设置已清除");
        }
    }

    @Override
    public int updateStudent(Student student) {
        try {
            DataSourceContextHolder.setDataSourceType("master"); // 写操作，切换到主库
            System.out.println("==> [DAO] 数据源切换至 [master] for updateStudent");
            String sql = "UPDATE student SET name = ?, gender = ?, age = ?, grade = ? WHERE id = ?";
            return jdbcTemplate.update(sql, student.getName(), student.getGender(), student.getAge(), student.getGrade(), student.getId());
        } finally {
            DataSourceContextHolder.clearDataSourceType();
            System.out.println("<== [DAO] 数据源设置已清除");
        }
    }

    @Override
    public Student getStudentById(Integer id) {
        try {
            DataSourceContextHolder.setDataSourceType("slave"); // 读操作，切换到从库
            System.out.println("==> [DAO] 数据源切换至 [slave] for getStudentById");
            String sql = "SELECT * FROM student WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null; // 查询不到结果时返回null
        } finally {
            DataSourceContextHolder.clearDataSourceType();
            System.out.println("<== [DAO] 数据源设置已清除");
        }
    }

    @Override
    public List<Student> getAllStudents() {
        try {
            DataSourceContextHolder.setDataSourceType("slave"); // 读操作，切换到从库
            System.out.println("==> [DAO] 数据源切换至 [slave] for getAllStudents");
            String sql = "SELECT * FROM student";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class));
        } finally {
            DataSourceContextHolder.clearDataSourceType();
            System.out.println("<== [DAO] 数据源设置已清除");
        }
    }

    /**
     * [模拟同步] 强制在从库上新增学生
     */
    public int addStudentOnSlave(Student student) {
        try {
            DataSourceContextHolder.setDataSourceType("slave"); // 强制切换到从库
            System.out.println("==> [SIMULATE SYNC] 强制在 [slave] 上执行 addStudent");
            String sql = "INSERT INTO student (id, name, gender, age, grade) VALUES (?, ?, ?, ?, ?)";
            // 注意：在从库插入时，我们必须手动指定ID，因为从库通常是只读的，没有自增ID
            return jdbcTemplate.update(sql, student.getId(), student.getName(), student.getGender(), student.getAge(), student.getGrade());
        } finally {
            DataSourceContextHolder.clearDataSourceType();
        }
    }

    /**
     * [模拟同步] 强制在从库上删除学生
     */
    public int deleteStudentByIdOnSlave(Integer id) {
        try {
            DataSourceContextHolder.setDataSourceType("slave"); // 强制切换到从库
            System.out.println("==> [SIMULATE SYNC] 强制在 [slave] 上执行 deleteStudent");
            String sql = "DELETE FROM student WHERE id = ?";
            return jdbcTemplate.update(sql, id);
        } finally {
            DataSourceContextHolder.clearDataSourceType();
        }
    }

    /**
     * [模拟同步] 强制在从库上更新学生
     */
    public int updateStudentOnSlave(Student student) {
        try {
            DataSourceContextHolder.setDataSourceType("slave"); // 强制切换到从库
            System.out.println("==> [SIMULATE SYNC] 强制在 [slave] 上执行 updateStudent");
            String sql = "UPDATE student SET name = ?, gender = ?, age = ?, grade = ? WHERE id = ?";
            return jdbcTemplate.update(sql, student.getName(), student.getGender(), student.getAge(), student.getGrade(), student.getId());
        } finally {
            DataSourceContextHolder.clearDataSourceType();
        }
    }
}