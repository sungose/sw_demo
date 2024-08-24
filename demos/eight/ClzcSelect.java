package com.echo.demos.eight;
import com.echo.utils.JDBCUtils;
import com.echo.utils.ToolUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;


// 总仓信息
public class ClzcSelect extends JInternalFrame implements InterfaceForm {
    ResultSet resultSet = null;
    DefaultTableModel dtm = new DefaultTableModel();
    JTable jTable = null;
    public ClzcSelect() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void ExecuteForm(JMenuItem src) {
        closable = true;
        try {
            ClzcSelect clzcSelect = new ClzcSelect();
            clzcSelect.setTable();
        } catch (Exception e) {

        }

    }


    public JTable setTable() throws SQLException {

        try {
            resultSet = JDBCUtils.getConnection().prepareStatement("select `hh`,`mc`,`ggxh`,`dw`,`sl`,`dj`,`je` from `clzc`").executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dtm.addColumn("货号");
        dtm.addColumn("名称");
        dtm.addColumn("规格型号");
        dtm.addColumn("单位");
        dtm.addColumn("数量");
        dtm.addColumn("单价");
        dtm.addColumn("金额");

        while (resultSet.next()) {
            Object[] row = new Object[7]; // 替换为实际的列数
            row[0] = resultSet.getString("hh");
            row[1] = resultSet.getString("mc");
            row[2] = resultSet.getString("ggxh");
            row[3] = resultSet.getString("dw");
            row[4] = resultSet.getInt("sl");
            row[5] = resultSet.getDouble("dj");
            row[6] = resultSet.getDouble("je");


            dtm.addRow(row);

//            return jTable;
        }
        jTable = new JTable(dtm);
        jTable.setPreferredScrollableViewportSize(new Dimension(900, 500));
        JPanel panel = new JPanel();
        panel.add(new JScrollPane(jTable)); // 使用滚动面板以便处理大量数据
        JFrame jFrame = new JFrame("总仓现存材料信息");
        jFrame.add(panel);
//        jFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());  // 将窗口伪最大化，窗口大小与屏幕尺寸相同，但不隐藏标题栏和任务栏
        ToolUtils.getCenter(jFrame,1000,550);
        return jTable;
    }
}
