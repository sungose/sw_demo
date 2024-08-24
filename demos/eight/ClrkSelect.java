package com.echo.demos.eight;
import com.echo.dao.CltzbDao;
import com.echo.utils.JDBCUtils;
import com.echo.utils.ToolUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

// 材料入库信息查询
public class ClrkSelect extends JInternalFrame implements InterfaceForm {

    private CltzbDao cltzbDao;
    ResultSet resultSet = null;
    DefaultTableModel dtm = new DefaultTableModel();
    JTable jTable = null;
    public ClrkSelect() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void ExecuteForm(JMenuItem src) {
        closable = true;
        try {
            ClrkSelect clrkSelect = new ClrkSelect();
            clrkSelect.setTable();
        } catch (Exception e) {

        }

    }


    public JTable setTable() throws SQLException {
        cltzbDao = new CltzbDao();
        String[] columnNames = {"名称", "规格型号", "货号", "单位", "库存数", "平均价", "库存总价"};

        try {
            resultSet = JDBCUtils.getConnection().prepareStatement("select `id`, `ckbh`, `hh`, `sl`, `dj`, `je`, `ywlb` from `clrk`").executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dtm.addColumn("id");
        dtm.addColumn("ckbh");
        dtm.addColumn("hh");
        dtm.addColumn("sl");
        dtm.addColumn("dj");
        dtm.addColumn("je");
        dtm.addColumn("ywlb");

        while (resultSet.next()) {
            Object[] row = new Object[7]; // 替换为实际的列数
            row[0] = resultSet.getInt("id");
            row[1] = resultSet.getString("ckbh");
            row[2] = resultSet.getString("hh");
            row[3] = resultSet.getInt("sl");
            row[4] = resultSet.getDouble("dj");
            row[5] = resultSet.getDouble("je");
            row[6] = resultSet.getString("ywlb");
            // 设置其他列的值...
            dtm.addRow(row);

//            return jTable;
        }
        jTable = new JTable(dtm);
        jTable.setPreferredScrollableViewportSize(new Dimension(900, 600));
        JPanel panel = new JPanel();
        panel.add(new JScrollPane(jTable)); // 使用滚动面板以便处理大量数据
        JFrame jFrame = new JFrame("入库表记录");
        jFrame.add(panel);
//        jFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());  // 将窗口伪最大化，窗口大小与屏幕尺寸相同，但不隐藏标题栏和任务栏

        ToolUtils.getCenter(jFrame,1000,650);
        return jTable;
    }
}
