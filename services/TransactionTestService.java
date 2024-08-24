package com.echo.services;

import com.echo.core.TransactionRunnable;
import com.echo.enums.InboundAndOutboundEnum;
import com.echo.utils.JDBCTransactionUtils;
import com.echo.utils.JDBCUtils;
import com.echo.utils.ToolUtils;

import java.security.SecureRandom;
import java.sql.*;
import java.util.Arrays;
import java.util.List;


/**
 * 事务业务类
 * 什么是事务(经典的案例就是：银行转账业务，A转账给B，那么A账户扣减余额，B账户增加余额。这个动作要么同时成功，要么失败!)
 */
public class TransactionTestService {


    /**
     * 新增出库
     * 我们的事务目标是：保证新增出库记录和出入库日志记录事务统一(2个动作都要成功，其中一个失败都失败)
     *
     * @param productId
     * @param quantity
     * @throws Exception
     */
    public void insertOutBound(String productId, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity is error");
        }

        // 获取链接
        Connection conn = JDBCUtils.getConnection();

        JDBCTransactionUtils.runTransaction(conn, new TransactionRunnable() {

            PreparedStatement pstmt1 = null;
            PreparedStatement pstmt2 = null;


            @Override
            public List<Statement> execute(Connection conn) throws Exception {
                // 新增出库操作
                String insertOutboundSQL = "INSERT INTO `outbound` (`product_id`, `quantity`, `outbound_date`" +
                        ", `created_at`) VALUES (?, ?, ?, ?)";

                pstmt1 = conn.prepareStatement(insertOutboundSQL, Statement.RETURN_GENERATED_KEYS);
                pstmt1.setString(1, productId);
                pstmt1.setInt(2, quantity);
                pstmt1.setInt(3, ToolUtils.timestampSecond());
                pstmt1.setInt(4, ToolUtils.timestampSecond());
                int updateOutboundNum = pstmt1.executeUpdate();

                // 获取出库记录的id
                int outboundId = -1;
                ResultSet rs = pstmt1.getGeneratedKeys();
                if (rs.next()) {
                    outboundId = rs.getInt(1);
                }
                System.out.println("新增出库记录 updateOutboundNum=" + updateOutboundNum + "\t outboundId=" + outboundId);


                // 新增出入库日志记录操作
                String insertOutboundLogSQL = "INSERT INTO `in_outbound_logs` (`ops`, `record_id`) VALUES (?, ?)";
                pstmt2 = conn.prepareStatement(insertOutboundLogSQL);
                pstmt2.setInt(1, InboundAndOutboundEnum.OUTBOUND.getCode());
                pstmt2.setInt(2, outboundId);
                int updateLogNum = pstmt2.executeUpdate();
                System.out.println("新增出入库日志记录 updateLogNum=" + updateLogNum);



                // 如何测试事务
                SecureRandom secureRandom = new SecureRandom();
                boolean hasError = secureRandom.nextBoolean();
                if (hasError) {
                    // 模拟程序错误
                    int i = 1 / 0;
                }

                return Arrays.asList(pstmt1, pstmt2);
            }
        });


    }

    // 1.拿到数据库连接 Conection
    // 2.一定要把连接的自动提交属性关闭，变成手动提交事物
    // 3.事务成功后就提交，事务失败就回滚


    public static void main(String[] args) throws Exception {
        TransactionTestService transactionTestService = new TransactionTestService();
        transactionTestService.insertOutBound("2201", 10);
    }
}