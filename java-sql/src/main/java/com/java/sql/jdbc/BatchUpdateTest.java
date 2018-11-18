package com.java.sql.jdbc;

import java.sql.*;
import java.util.Arrays;

/**
 * @Project: jdk
 * @description:  批量更新
 * @author: sunkang
 * @create: 2018-10-14 15:46
 * @ModificationHistory who      when       What
 **/
public class BatchUpdateTest{
    public static void main(String[] args) {
        BatchUpdateTest batchUpdateTest = new BatchUpdateTest();
        Connection con =batchUpdateTest.getDefalutConnection();
        Statement statement = null;
        try {
            //默认的事务提交类型是自动提交，但是在这里需要设置自动提交类型为false,在出现错误的时候需要回滚
            con.setAutoCommit(false);
             statement  =  con.createStatement();
            for(int i=0;i<10;i++){
                String username = "sunkang"+ i;
                statement.executeUpdate("insert user(username,birthday,sex,address) values ('"+username+"',NOW(),1,'hanghzou')");
            }
            //表示批量语句集返回的受影响的行数
            int[]  updateCounts  =  statement.executeBatch();

            System.out.println(Arrays.toString(updateCounts));

            String sql = "insert user(username,birthday,sex,address) values (?,now(),?,?)";
            PreparedStatement preparedStatement =  con.prepareStatement(sql);
            for(int i=10;i<12;i++){
                String username = "sunkang"+ i;
                preparedStatement.setString(1,username);
                preparedStatement.setString(2,"1");
                preparedStatement.setString(3,"hanghzou");
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            con.commit();

        } catch (SQLException e) {
            //出现异常的时候回滚事务
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            //关闭语句集
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                //设置回来
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public Connection getDefalutConnection(){
        Connection connection = null;
        try {
            //1.加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.通过驱动管理类获取数据库链接
            connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8", "root", "123");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
