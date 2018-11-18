package com.java.sql.jdbc;

import java.sql.*;

/**
 * @Project: jdk
 * @description:
 * @author: sunkang
 * @create: 2018-10-14 10:39
 * @ModificationHistory who      when       What
 **/
public class StatementTest {

    public static void main(String[] args) throws SQLException {
               Connection con =null;
        String sql  ="select * from user ";
        String proceduresOrFuntionSql = "{call  UserTest()}";

        //Statement　： 不带参数的简单语句集
        Statement statement  = con.createStatement();
        //PreparedStatement ：预编译的sqL语句集，包含输入参数
        PreparedStatement  preparedStatement= con.prepareStatement(sql);
        //CallableStatement：用于执行存储过程和方法，包含输入参数和输出参数的设置，这个会在存储过程的章节讲解
        CallableStatement callableStatement = con.prepareCall(proceduresOrFuntionSql);

        //如果查询返回的第一个对象是ResultSet对象，则返回true,则可以调用Statement.getResultSet来获取ResultSet对象
        boolean tag =statement.execute(sql);
        if(tag){
            ResultSet resultSet = statement.getResultSet();
            //下面可以进行对象的映射
        }
        //返回一个result对象
        ResultSet resultSet =  statement.executeQuery(sql);
        //返回受sqL语句的影响的行数的整数，但你用insert,delete,update语句集的时候使用
        int  affectRow=statement.executeUpdate(sql);

        //增加到一个批次的SQL语句集中
        statement.addBatch(sql);
        //返回的是一个数组，一次性执行sql的批量语句集,返回的是一个int数组对象，表示每个语句集受影响的行数
        int[] affectRows =  statement.executeBatch();

        //预编译的语句集执行不需要指明sql 语句，创建的时候指定了
        preparedStatement.execute();
        preparedStatement.executeQuery();
        preparedStatement.executeUpdate();


        preparedStatement.addBatch();
        preparedStatement.executeBatch();



    }



}
