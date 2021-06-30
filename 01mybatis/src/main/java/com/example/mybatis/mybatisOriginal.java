package com.example.mybatis;

import java.sql.*;
import com.example.vo.uservo;

/**
 * @author oxygenxyl
 * @create 2021-06-29 22:45
 */
public class mybatisOriginal {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //通过驱动管理类获取数据库链接
            connection = DriverManager.getConnection("jdbc:mysql://139.196.46.164:3306/dai0?characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai", "root", "123456");
            //定义sql语句，？表示占位符
            String sql = "select * from user where username = ?";
            //获取预处理statement
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "tom");
            resultSet = preparedStatement.executeQuery();
            uservo uservo = null;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                uservo = new uservo();
                uservo.setId(id);
                uservo.setUsername(username);
            }
            System.out.println(uservo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


