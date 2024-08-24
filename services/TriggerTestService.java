
package com.echo.services;

import com.echo.utils.JDBCUtils;
import com.echo.utils.ToolUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * 触发器业务类
 * 当新增入库记录时，出入库的日志记录表也会新增一条记录
 */
public class TriggerTestService {


    // 新增入库
    public void insertInBound(String productId, int quantity) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt1 = null;

        try {
            if (quantity <= 0) {
                throw new IllegalArgumentException("quantity is error");
            }

            // 获取链接
            conn = JDBCUtils.getConnection();

            // SQL
            String insertSQL = "INSERT INTO `inbound` (`product_id`, `quantity`, `inbound_date`, `created_at`) VALUES " +
                    "(?, ?, ?, ?)";

            pstmt1 = conn.prepareStatement(insertSQL);
            pstmt1.setString(1, productId);
            pstmt1.setInt(2, quantity);
            pstmt1.setInt(3, ToolUtils.timestampSecond());
            pstmt1.setInt(4, ToolUtils.timestampSecond());
            int updateNum = pstmt1.executeUpdate();


            System.out.println("触发器测试类 新增入库记录 updateNum=" + updateNum + "\t 可以去库中查看出入库日志记录是否新增成功");
        } finally {
            JDBCUtils.close(conn,pstmt1 );
//
//            try {
//                if (pstmt1 != null) {
//                    pstmt1.close();
//                }
//
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }
    }


    public static void main(String[] args) throws Exception {
        TriggerTestService triggerTestService = new TriggerTestService();
        triggerTestService.insertInBound("2202", 5);
    }
}