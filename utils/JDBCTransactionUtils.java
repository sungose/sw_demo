package com.echo.utils;

import com.echo.core.TransactionRunnable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 事务工具类
 */
public class JDBCTransactionUtils {


    /**
     * 事务流程模板方法
     *
     * @param conn
     * @param transactionRunnable
     */
    public static void runTransaction(Connection conn, TransactionRunnable transactionRunnable) {
        List<Statement> statementList = null;

        try {
            // 禁用自动提交，开始事务
            conn.setAutoCommit(false);

            statementList = transactionRunnable.execute(conn);


            // 手动提交事务
            conn.commit();
            System.out.println("Transaction committed successfully.");
        } catch (Exception e) {
            e.printStackTrace();

            // 回滚事务
            try {
                conn.rollback();
                System.out.println("Transaction rolled back.");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            // 最后关闭连接资源
            JDBCUtils.close(conn, null, null);

            // 关闭statement资源
            if (ToolUtils.notEmpty(statementList)) {
                for (Statement statement : statementList) {
                    JDBCUtils.close(null, statement, null);
                }
            }
        }
    }


}
