package com.echo.demos.eight;
import com.echo.utils.JDBCUtils;
import com.echo.utils.ToolUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClllSelect extends JInternalFrame implements InterfaceForm {
    ResultSet resultSet = null;
    DefaultTableModel dtm = new DefaultTableModel();
    JTable jTable = null;
    public ClllSelect() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void ExecuteForm(JMenuItem src) {
        closable = true;
        try {
            ClllSelect clllSelect = new ClllSelect();
            clllSelect.setTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public JTable setTable() throws SQLException {

        try {
            resultSet = JDBCUtils.getConnection().prepareStatement("select `ll_id`,`hh`,`ckbh`,`sl`,`lysj`,`recipient`,`status`,`day` from `clll`").executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dtm.addColumn("领料编号");
        dtm.addColumn("货号");
        dtm.addColumn("仓库编号");
        dtm.addColumn("领用数量");
        dtm.addColumn("领用时间");
        dtm.addColumn("领用人");
        dtm.addColumn("领用状态");
        dtm.addColumn("登记时间");

        while (resultSet.next()) {
            Object[] row = new Object[8]; // 替换为实际的列数
            row[0] = resultSet.getInt("ll_id");
            row[1] = resultSet.getString("hh");
            row[2] = resultSet.getString("ckbh");
            row[3] = resultSet.getInt("sl");
            row[4] = resultSet.getDate("lysj");
            row[5] = resultSet.getString("recipient");
            row[6] = resultSet.getString("status");
            row[7] = resultSet.getString("day");

            dtm.addRow(row);

//            return jTable;
        }
        jTable = new JTable(dtm);
        jTable.setPreferredScrollableViewportSize(new Dimension(900, 600));
        JPanel panel = new JPanel();
        panel.add(new JScrollPane(jTable)); // 使用滚动面板以便处理大量数据
        JFrame jFrame = new JFrame("材料领用记录");
        jFrame.add(panel);
//        jFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());  // 将窗口伪最大化，窗口大小与屏幕尺寸相同，但不隐藏标题栏和任务栏
        ToolUtils.getCenter(jFrame,1000,650);
        return jTable;
    }
}