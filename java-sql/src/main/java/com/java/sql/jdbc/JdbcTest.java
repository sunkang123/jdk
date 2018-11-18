package com.java.sql.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project: java-sql
 * @description: jdbc的简单测试类
 * @author: sunkang
 * @create: 2018-10-07 12:04
 * @ModificationHistory who      when       What
 **/
public class JdbcTest {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //1.加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.通过驱动管理类获取数据库链接
            connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8", "root", "123");
            //定义sql语句 ?表示占位符
            String sql = "select * from user where username = ?";
            //3.使用连接获取预处理statement
            preparedStatement = connection.prepareStatement(sql);
            //4.为预处理的statement设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
            preparedStatement.setString(1, "王五");
            //5.执行sql执行查询，得出出结果集
            resultSet =  preparedStatement.executeQuery();

            //6.遍历查询结果集，进行结果集的映射成对象
            List<User> userList = new ArrayList<User>();
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setSex(resultSet.getString("sex"));
                user.setBirthday(resultSet.getTime("birthday"));
                user.setAddress(resultSet.getString("address"));
                userList.add(user);
            }
            //输出结果
            System.out.println(userList.toString());

        } catch (Exception e) {
            //7. 处理异常
            e.printStackTrace();
        }finally{
            //8. 释放资源，依次从结果集、statement，数据库连接一次释放，顺序不能反了
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
