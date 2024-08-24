package com.echo.demos.eight;
import com.echo.utils.JDBCUtils;
import com.echo.utils.ToolUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClbcSelect extends JInternalFrame implements InterfaceForm {
    ResultSet resultSet = null;
    DefaultTableModel dtm = new DefaultTableModel();
    JTable jTable = null;
    public ClbcSelect() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void ExecuteForm(JMenuItem src) {
        closable = true;
        try {
            ClbcSelect clbcSelect = new ClbcSelect();
            clbcSelect.setTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public JTable setTable() throws SQLException {

        try {
            resultSet = JDBCUtils.getConnection().prepareStatement("select `id`,`ckbh`,`hh`,`sl`,`dj`,`je`,`admin` from `clbc`").executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dtm.addColumn("ID");
        dtm.addColumn("仓库编号");
        dtm.addColumn("货号");
        dtm.addColumn("数量");
        dtm.addColumn("单价");
        dtm.addColumn("金额");
        dtm.addColumn("管理员");

        while (resultSet.next()) {
            Object[] row = new Object[7]; // 替换为实际的列数
            row[0] = resultSet.getInt("id");
            row[1] = resultSet.getString("ckbh");
            row[2] = resultSet.getString("hh");
            row[3] = resultSet.getInt("sl");
            row[4] = resultSet.getDouble("dj");
            row[5] = resultSet.getDouble("je");
            row[6] = resultSet.getString("admin");

            dtm.addRow(row);

//            return jTable;
        }
        jTable = new JTable(dtm);
        jTable.setPreferredScrollableViewportSize(new Dimension(900, 600));
        JPanel panel = new JPanel();
        panel.add(new JScrollPane(jTable)); // 使用滚动面板以便处理大量数据
        JFrame jFrame = new JFrame("仓库信息");
        jFrame.add(panel);
//        jFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());  // 将窗口伪最大化，窗口大小与屏幕尺寸相同，但不隐藏标题栏和任务栏
        ToolUtils.getCenter(jFrame,1000,650);
        return jTable;
    }
}
