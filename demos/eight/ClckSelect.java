package com.echo.demos.eight;
import com.echo.dao.CltzbDao;
import com.echo.utils.JDBCUtils;
import com.echo.utils.ToolUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

// 材料台账表查询
public class ClckSelect extends JInternalFrame implements InterfaceForm {

    private CltzbDao cltzbDao;
    ResultSet resultSet = null;
    DefaultTableModel dtm = new DefaultTableModel();
    JTable jTable = null;
    public ClckSelect() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void ExecuteForm(JMenuItem src) {
        closable = true;
        try {
            ClckSelect clckSelect = new ClckSelect();
            clckSelect.setTable();
        } catch (Exception e) {

        }

    }


    public JTable setTable() throws SQLException {
        cltzbDao = new CltzbDao();

        try {
            resultSet = JDBCUtils.getConnection().prepareStatement("select * from `cltz`").executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dtm.addColumn("id");
        dtm.addColumn("ckbh");
        dtm.addColumn("hh");
        dtm.addColumn("grsl");
        dtm.addColumn("grdj");
        dtm.addColumn("grje");
        dtm.addColumn("lysl");
        dtm.addColumn("lydj");
        dtm.addColumn("lyje");
        dtm.addColumn("lysl");
        dtm.addColumn("jcdj");
        dtm.addColumn("jcje");
        dtm.addColumn("ywlb");

        while (resultSet.next()) {
            Object[] row = new Object[14]; // 替换为实际的列数
            row[0] = resultSet.getInt("id");
            row[1] = resultSet.getString("ckbh");
            row[2] = resultSet.getString("hh");
            row[3] = resultSet.getInt("grsl");
            row[5] = resultSet.getDouble("grdj");
            row[6] = resultSet.getDouble("grje");
            row[7] = resultSet.getInt("lysl");
            row[8] = resultSet.getDouble("lydj");
            row[9] = resultSet.getDouble("lyje");
            row[10] = resultSet.getInt("jcsl");
            row[11] = resultSet.getDouble("jcdj");
            row[12] = resultSet.getDouble("jcje");
            row[13] = resultSet.getString("ywlb");
            // 设置其他列的值...
            dtm.addRow(row);

//            return jTable;
        }
        jTable = new JTable(dtm);
        jTable.setPreferredScrollableViewportSize(new Dimension(900, 600));
        JPanel panel = new JPanel();
        panel.add(new JScrollPane(jTable)); // 使用滚动面板以便处理大量数据
        JFrame jFrame = new JFrame("台账表记录");
        jFrame.add(panel);
//        jFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());  // 将窗口伪最大化，窗口大小与屏幕尺寸相同，但不隐藏标题栏和任务栏
        ToolUtils.getCenter(jFrame,1000,650);
        return jTable;
    }
}
