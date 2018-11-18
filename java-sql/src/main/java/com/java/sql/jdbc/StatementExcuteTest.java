package com.java.sql.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project: jdk
 * @description:
 * @author: sunkang
 * @create: 2018-10-14 11:00
 * @ModificationHistory who      when       What
 **/
public class StatementExcuteTest {

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
            String sql = "select * from user ";
            //3.使用连接获取预处理statement
            Statement statement = connection.createStatement();
            List<User> userList = new ArrayList<User>();
            if(statement.execute(sql)){
                resultSet = statement.getResultSet();
                //6.遍历查询结果集，进行结果集的映射成对象
                while(resultSet.next()){
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setSex(resultSet.getString("sex"));
                    user.setBirthday(resultSet.getTimestamp("birthday"));
                    user.setAddress(resultSet.getString("address"));
                    userList.add(user);
                }
            }



            //输出结果
            System.out.println(userList.toString());

            System.out.println(statement.execute("update user set username = 'sunkang' where id  = 1"));

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
