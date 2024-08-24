package com.echo.demos.eight;
import com.echo.utils.JDBCUtils;
import com.echo.utils.ToolUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用户信息
 */

public class UserSelect extends JInternalFrame implements InterfaceForm {
    ResultSet resultSet = null;
    DefaultTableModel dtm = new DefaultTableModel();
    JTable jTable = null;
    public UserSelect() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void ExecuteForm(JMenuItem src) {
        closable = true;
        try {
            UserSelect userSelect = new UserSelect();
            userSelect.setTable();
        } catch (Exception e) {

        }

    }


    public JTable setTable() throws SQLException {
        try {
            resultSet = JDBCUtils.getConnection().prepareStatement("select `user_id`, `username`, `password`, `role`, `created_at` from `users`").executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dtm.addColumn("用户序号");
        dtm.addColumn("用户姓名");
        dtm.addColumn("用户密码");
        dtm.addColumn("职位");
        dtm.addColumn("用户创建时间");

        while (resultSet.next()) {
            Object[] row = new Object[5]; // 替换为实际的列数
            row[0] = resultSet.getInt("user_id");
            row[1] = resultSet.getString("username");
            row[2] = resultSet.getString("password");
            row[3] = resultSet.getString("role");
            row[4] = resultSet.getString("created_at");
            // 设置其他列的值...
            dtm.addRow(row);

//            return jTable;
        }
        jTable = new JTable(dtm);
        jTable.setPreferredScrollableViewportSize(new Dimension(900, 600));
        JPanel panel = new JPanel();
        panel.add(new JScrollPane(jTable)); // 使用滚动面板以便处理大量数据
        JFrame jFrame = new JFrame("用户信息");
        jFrame.add(panel);
//        jFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());  // 将窗口伪最大化，窗口大小与屏幕尺寸相同，但不隐藏标题栏和任务栏

        ToolUtils.getCenter(jFrame,1000,650);
        return jTable;
    }
}
