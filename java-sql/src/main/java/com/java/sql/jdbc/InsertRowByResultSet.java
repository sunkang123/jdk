package com.java.sql.jdbc;

import javax.xml.transform.Result;
import java.sql.*;

/**
 * @Project: jdk
 * @description:  插入的测试
 * @author: sunkang
 * @create: 2018-10-14 16:18
 * @ModificationHistory who      when       What
 **/
public class InsertRowByResultSet {

    public static void main(String[] args) throws SQLException {
        ResultSetTest  resultSetTest = new ResultSetTest();
        Connection con =  resultSetTest.getDefalutConnection();
        Statement statement  = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        String sql = "select * from user";
        ResultSet resultSet  = statement.executeQuery(sql);
        //移动游标到插入的行
        resultSet.moveToInsertRow();
        //插入一行数据
        resultSet.updateString("username","wangzhi");
        resultSet.updateString("sex","1");
        resultSet.updateTimestamp("birthday",new Timestamp(System.currentTimeMillis()));
        resultSet.updateString("address","han");

        //插入该条记录
        resultSet.insertRow();
        //游标移动到最开始的地方
        resultSet.beforeFirst();
        //释放资源
        resultSet.close();
        statement.close();
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
