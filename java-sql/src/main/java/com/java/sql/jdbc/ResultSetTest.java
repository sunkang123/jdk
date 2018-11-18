package com.java.sql.jdbc;

import java.sql.*;

/**
 * @Project: jdk
 * @description: mysql的ResultSet测试
 * @author: sunkang
 * @create: 2018-10-14 14:33
 * @ModificationHistory who      when       What
 **/
public class ResultSetTest {

    public static void main(String[] args) throws SQLException {

        ResultSetTest  resultSetTest = new ResultSetTest();

        Connection con =  resultSetTest.getDefalutConnection();

        //验证mysql 支持哪些ResultSet类型
        DatabaseMetaData dbMetaData    = con.getMetaData();
        System.out.println("Supports TYPE_FORWARD_ONLY? "+dbMetaData.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY));
        System.out.println("Supports TYPE_SCROLL_INSENSITIVE? "+dbMetaData.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
        System.out.println("Supports TYPE_SCROLL_SENSITIVE? "+dbMetaData.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));
       /* 上面的输出结果为：
        Supports TYPE_FORWARD_ONLY? false
        Supports TYPE_SCROLL_INSENSITIVE? true
        Supports TYPE_SCROLL_SENSITIVE? false
        说明mysql支持类型为 TYPE_SCROLL_INSENSITIVE
        */

        //验证mysql 支持哪些ResultSet并发性
        System.out.println("Supports CONCUR_READ_ONLY? "+dbMetaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY));
        System.out.println("Supports CONCUR_UPDATABLE? "+dbMetaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE));

      /* 上面的输出结果为：
        Supports CONCUR_READ_ONLY? true
        Supports CONCUR_UPDATABLE? true
        说明mysql 可以在ResultSet对象更改结果集
        */

        //验证mysql 支持哪些游标保持性
        System.out.println("Supports HOLD_CURSORS_OVER_COMMIT? "+dbMetaData.supportsResultSetHoldability(ResultSet.HOLD_CURSORS_OVER_COMMIT));
        System.out.println("Supports CLOSE_CURSORS_AT_COMMIT? "+dbMetaData.supportsResultSetHoldability(ResultSet.CLOSE_CURSORS_AT_COMMIT));

        //验证mysql的支持数据隔离级别：
        //设置事务隔离级别 con.setTransactionIsolation();
        System.out.println("defalut TransactionIsolation"+ con.getTransactionIsolation());
        System.out.println("Supports TRANSACTION_NONE? "+ dbMetaData.supportsTransactionIsolationLevel(Connection.TRANSACTION_NONE));
        System.out.println("Supports TRANSACTION_READ_UNCOMMITTED? "+ dbMetaData.supportsTransactionIsolationLevel(Connection.TRANSACTION_READ_UNCOMMITTED));
        System.out.println("Supports TRANSACTION_READ_COMMITTED? "+ dbMetaData.supportsTransactionIsolationLevel(Connection.TRANSACTION_READ_COMMITTED));
        System.out.println("Supports TRANSACTION_REPEATABLE_READ? "+ dbMetaData.supportsTransactionIsolationLevel(Connection.TRANSACTION_REPEATABLE_READ));
        System.out.println("Supports TRANSACTION_SERIALIZABLE? "+ dbMetaData.supportsTransactionIsolationLevel(Connection.TRANSACTION_SERIALIZABLE));

        /**
         *输出的结果为：
         * defalut TransactionIsolation ? 4
         * Supports TRANSACTION_NONE? false
         * Supports TRANSACTION_READ_UNCOMMITTED? true
         * Supports TRANSACTION_READ_COMMITTED? true
         * Supports TRANSACTION_REPEATABLE_READ? true
         * Supports TRANSACTION_SERIALIZABLE? true
         *默认的是 支持可重复读
         * mysql 支持下面四种数据库隔离级别
         */


        /**
         * Supports HOLD_CURSORS_OVER_COMMIT? true
         * Supports CLOSE_CURSORS_AT_COMMIT? false
         * 说明mysql在commit提交之后，游标还是打开的
         */
        //创建了一个类型为HOLD_CURSORS_OVER_COMMIT，并发性为：CONCUR_UPDATABLE，以及游标的保持性为：HOLD_CURSORS_OVER_COMMIT的语句集
       Statement statement  =  con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM user");

        //设置连接的自动提交为false
        con.setAutoCommit(false);
        //更新user 的address统一后面加上：update语句
        while (resultSet.next()) {
            //address为大写和小写都是可以的
            String address = resultSet.getString("address");
            resultSet.updateString("address", address+":update");
            //更新此行
            resultSet.updateRow();
            //在这里提交，游标还是打开的，所以不会有问题
            con.commit();
        }
        //释放资源
        resultSet.close();
        statement.close();
        con.close();
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
