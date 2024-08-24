package com.echo.demos.eight;

import com.echo.dao.ClggbDao;
import com.echo.utils.ToolUtils;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

// 材料规格表查询模版
public class TableListApp extends JFrame {

//    public static void main(String[] args) {
//        new TableListApp();
//    }


    private int currentPage = 1;

    private int pageSize = 10;
    private int lastPage;

    private String mc;

    JTable table = null;
    DefaultTableModel dtm = null;
    JScrollPane jsp = null;


    JButton button1 = null;

    private ClggbDao clggbDao;

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getMc() {
        return mc;
    }

    public TableListApp() {
        clggbDao = new ClggbDao();

        String[] columnNames = {"名称", "规格型号", "货号", "单位", "库存数", "平均价", "库存总价"};
        dtm = new DefaultTableModel(columnNames, 0);


        dtm.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int type = e.getType();

                if (TableModelEvent.UPDATE == type) {
                    // 获取所选数据的行数
                    int row = e.getFirstRow();

                    // 获得此行第1列的值
                    Object val = dtm.getValueAt(row, 0);
                    System.out.println("val ==" + val);


                    // todo 如何和数据库进行交互

                    // 系统重新绘制表格
                    table.repaint();
                }
            }
        });
        table = new JTable(dtm);


        jsp = new JScrollPane();
        jsp.setViewportView(table);
        getContentPane().add(jsp);

        this.setTitle("材料规格表");
        ToolUtils.getCenter(this,600,400);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        this.showTable(currentPage);


        JPanel jPanelNORTH = new JPanel();
        getContentPane().add(jPanelNORTH, BorderLayout.NORTH);

        JLabel labelName = new JLabel("名称");

        // 创建JTextField，16表示16列，用于JTextField的宽度显示而不是限制字符个数
        JTextField nameField = new JTextField(16);
        JButton nameButton = new JButton("查询");


        // 添加控件
        jPanelNORTH.setLayout(new FlowLayout());
        jPanelNORTH.add(labelName);
        jPanelNORTH.add(nameField);
        jPanelNORTH.add(nameButton);


        nameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = nameField.getText();

                if(Objects.isNull(value) || value.trim().isEmpty()) {
                    // 什么情况下，需要进行弹框提示
                    JOptionPane.showOptionDialog(jPanelNORTH, "您还没有输入名称", "提示"
                            , JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                    return;
                }

                setMc(value);
                showTable(1);
            }
        });


        // 底部面榜
        JPanel southPanel = new JPanel();
        getContentPane().add(southPanel, BorderLayout.SOUTH);


        // 按钮监听时间
        MyTable myTable = new MyTable();

        JButton button = new JButton("首页");
        button.addActionListener(myTable);
        button.setActionCommand("首页");
        southPanel.add(button);
        button1 = new JButton("上一页");
        button1.addActionListener(myTable);
        southPanel.add(button1);
        JButton button2 = new JButton("下一页");
        button2.addActionListener(myTable);
        southPanel.add(button2);
        JButton button3 = new JButton("末页");
        button3.addActionListener(myTable);
        southPanel.add(button3);
        setVisible(true);

    }

    public void showTable(int currentPage) {
        // 清除原有行
        dtm.setRowCount(0);

        // 获取表总记录数
        int total = clggbDao.getCount();
        if (total % pageSize == 0) {
            setLastPage(total / getPageSize());
        } else {
            setLastPage(total / getPageSize() + 1);
        }

        this.setCurrentPage(currentPage);


        // 查询列表
        List<Vector> pageList = clggbDao.getPageList(getMc(), currentPage, pageSize);
        pageList.forEach(rowV -> {
            dtm.addRow(rowV);
        });

        // table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //关闭表格列的自动调整功能
        // table.setSelectionBackground(Color.YELLOW);
        // table.setSelectionForeground(Color.RED);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(30);
    }




    /**
     * 按钮监听类
     */
    class MyTable implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("首页")) {
                showTable(1);
            }

            if (e.getActionCommand().equals("上一页")) {
                if (getCurrentPage() <= 1) {
                    setCurrentPage(2);
                }
                showTable(getCurrentPage() - 1);
            }

            if (e.getActionCommand().equals("下一页")) {
                if (getCurrentPage() < getLastPage()) {
                    showTable(getCurrentPage() + 1);
                } else {
                    showTable(getLastPage());
                }
            }

            if (e.getActionCommand().equals("末页")) {
                showTable(getLastPage());
            }
        }
    }

}