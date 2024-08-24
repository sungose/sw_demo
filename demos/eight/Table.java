package com.echo.demos.eight;

import com.echo.dao.ClggbDao;
import com.echo.javabean.Clgg;
import com.echo.utils.JDBCUtils;

import java.awt.BorderLayout;
import java.awt.Container;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static jdk.nashorn.internal.objects.NativeArray.forEach;

public class Table {
    DefaultTableModel dtm = new DefaultTableModel();
    JTable jTable = null;
    JScrollPane jsp = null;
    Container container;
    private ClggbDao clggbDao;
    ResultSet resultSet = null;
    private Clgg clgg;
    public JTable setTable() throws SQLException {
        clggbDao = new ClggbDao();
        clgg = new Clgg();
        int count = clggbDao.getCount();
//        String[] columnNames = {"名称", "规格型号", "货号", "单位", "库存数", "平均价", "库存总价"};
//        dtm = new DefaultTableModel(columnNames, 0);

//        container.setLayout(new BorderLayout());
//        container.add(jsp);
                    resultSet = JDBCUtils.getConnection().prepareStatement("select `mc`, `ggxh`, `hh`, `dw`, `kcs`, `pjj`, `kczj` from `clggb`").executeQuery();
//        clgg = clggbDao.selectAll();

        dtm.addColumn("mc");
        dtm.addColumn("ggxh");
        dtm.addColumn("hh");
        dtm.addColumn("dw");
        dtm.addColumn("kcs");
        dtm.addColumn("pjj");
        dtm.addColumn("kczj");

        while (resultSet.next()) {
            Object[] row = new Object[7]; // 替换为实际的列数
            row[0] = resultSet.getString("mc");
            row[1] = resultSet.getString("ggxh");
            row[2] = resultSet.getString("hh");
            row[3] = resultSet.getString("dw");
            row[4] = resultSet.getDouble("kcs");
            row[5] = resultSet.getDouble("pjj");
            row[6] = resultSet.getDouble("kczj");
            // 设置其他列的值...
            dtm.addRow(row);

//            return jTable;
        }
        jTable = new JTable(dtm);
        JPanel panel = new JPanel();
        panel.add(new JScrollPane(jTable)); // 使用滚动面板以便处理大量数据
//        JFrame jFrame = new JFrame();
//        jFrame.add(panel);
//        jFrame.setSize(500,500);
//        jFrame.setVisible(true);
        return jTable;
    }

    public static void main(String[] args) throws SQLException {
        Table table = new Table();
        table.setTable();
    }
}