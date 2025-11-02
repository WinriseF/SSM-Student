package com.java.dao;

import com.java.datasource.DataSourceContextHolder;
import com.java.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 新增用户 (写操作 -> 主库)
     */
    @Override
    public void addUser(User user) {
        try {
            // 写操作，设置数据源为 master
            DataSourceContextHolder.setDataSourceType("master");
            System.out.println("==> [DAO] 数据源切换至 [master]");
            String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
            jdbcTemplate.update(sql, user.getName(), user.getAge());
        } finally {
            // 操作结束，清理数据源设置，确保不会影响后续操作
            DataSourceContextHolder.clearDataSourceType();
            System.out.println("<== [DAO] 数据源设置已清除");
        }
    }

    /**
     * 删除用户 (写操作 -> 主库)
     */
    @Override
    public void deleteUser(Integer id) {
        try {
            // 写操作，设置数据源为 master
            DataSourceContextHolder.setDataSourceType("master");
            System.out.println("==> [DAO] 数据源切换至 [master]");
            String sql = "DELETE FROM user WHERE id = ?";
            jdbcTemplate.update(sql, id);
        } finally {
            // 操作结束，清理数据源设置
            DataSourceContextHolder.clearDataSourceType();
            System.out.println("<== [DAO] 数据源设置已清除");
        }
    }

    /**
     * 更新用户 (写操作 -> 主库)
     */
    @Override
    public void updateUser(User user) {
        try {
            // 写操作，设置数据源为 master
            DataSourceContextHolder.setDataSourceType("master");
            System.out.println("==> [DAO] 数据源切换至 [master]");
            String sql = "UPDATE user SET name = ?, age = ? WHERE id = ?";
            jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getId());
        } finally {
            // 操作结束，清理数据源设置
            DataSourceContextHolder.clearDataSourceType();
            System.out.println("<== [DAO] 数据源设置已清除");
        }
    }

    /**
     * 根据ID查询用户 (读操作 -> 从库)
     */
    @Override
    public User getUserById(Integer id) {
        try {
            // 读操作，设置数据源为 slave
            DataSourceContextHolder.setDataSourceType("slave");
            System.out.println("==> [DAO] 数据源切换至 [slave]");
            String sql = "SELECT * FROM user WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        } catch (EmptyResultDataAccessException e) {
            // 如果查询结果为空，queryForObject会抛出异常，这里捕获并返回null，是更友好的处理方式
            return null;
        } finally {
            // 操作结束，清理数据源设置
            DataSourceContextHolder.clearDataSourceType();
            System.out.println("<== [DAO] 数据源设置已清除");
        }
    }

    /**
     * 查询所有用户 (读操作 -> 从库)
     */
    @Override
    public List<User> getAllUsers() {
        try {
            // 读操作，设置数据源为 slave
            DataSourceContextHolder.setDataSourceType("slave");
            System.out.println("==> [DAO] 数据源切换至 [slave]");
            String sql = "SELECT * FROM user";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        } finally {
            // 操作结束，清理数据源设置
            DataSourceContextHolder.clearDataSourceType();
            System.out.println("<== [DAO] 数据源设置已清除");
        }
    }
}