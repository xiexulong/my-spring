package com.xxl.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**

 CREATE TABLE `xxl_jdbc`.`t_student` (
 `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
 `name` VARCHAR(45) NULL,
 `age` INT NULL,
 PRIMARY KEY (`id`),
 UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);

 */
public class MysqlJdbcDemo {

//    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    // mysql 8.0 版本去掉由com.mysql.jdbc.Driver 改为 com.mysql.cj.jdbc.Driver
    private static final String DB_URL = "jdbc:mysql://localhost:3306/xxl_jdbc?characterEncoding=utf-8";
    private static final String PASSWORD = "helloroot";
    private static final String USER = "root";
    public static void main(String[] args) {
        MysqlJdbcDemo mysqlJdbcDemo = new MysqlJdbcDemo();
        mysqlJdbcDemo.insertDemo("xxl", 2);
        mysqlJdbcDemo.queryDemo();
        mysqlJdbcDemo.updateDemo("xxl", 3, 1);
        mysqlJdbcDemo.queryDemo();
        mysqlJdbcDemo.deleteDemo(4);
        mysqlJdbcDemo.queryDemo();
    }

    public int insertDemo(String name, int age) {
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "insert into t_student(name,age) value(?,?)";
        try {
            // 注册jdbc驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            // 创建预编译
            ps = connection.prepareStatement(sql);
            // 设置预编译语句对象占位符对应的参数值
            ps.setString(1, name);
            ps.setInt(2, age);
            //执行sql语句，注意这里方法不能带sql参数
            int row = ps.executeUpdate();

            return row;
        } catch (SQLException se) {
            //处理jdbc 错误
            se.getStackTrace();
        } catch (Exception e) {
            //处理Class.forName错误
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }
        return 0;
    }

    public int deleteDemo(int id){
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "delete from t_student where id = ?";
        try {
            // 注册jdbc驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            // 创建预编译
            ps = connection.prepareStatement(sql);
            // 设置预编译语句对象占位符对应的参数值
            ps.setInt(1, id);
            //执行sql语句，注意这里方法不能带sql参数
            int row = ps.executeUpdate();

            return row;
        } catch (SQLException se) {
            //处理jdbc 错误
            se.getStackTrace();
        } catch (Exception e) {
            //处理Class.forName错误
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }
        return 0;
    }

    public int updateDemo(String name, int age, int id){
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "update t_student set name = ?,age = ? where id = ?";
        try {
            // 注册jdbc驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            // 创建预编译
            ps = connection.prepareStatement(sql);
            // 设置预编译语句对象占位符对应的参数值
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setInt(3, id);
            //执行sql语句，注意这里方法不能带sql参数
            int row = ps.executeUpdate();

            return row;
        } catch (SQLException se) {
            //处理jdbc 错误
            se.getStackTrace();
        } catch (Exception e) {
            //处理Class.forName错误
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }
        return 0;
    }

    public void queryDemo(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from t_student";
        try {
            // 注册jdbc驱动
            Class.forName(JDBC_DRIVER);
            //打开链接
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println("id:" + id + ", name:" + name + ", age:" + age);
            }
        } catch (SQLException se) {
            //处理jdbc 错误
            se.getStackTrace();
        } catch (Exception e) {
            //处理Class.forName错误
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException se) {
                se.getStackTrace();
            }
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }
    }
}
