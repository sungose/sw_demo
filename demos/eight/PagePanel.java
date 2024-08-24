package com.echo.demos.eight;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class PagePanel extends JTabbedPane {

    private JTable jTable;
    private JTree jTree;
    private Tree tree;
    private Table table;

    public PagePanel() throws SQLException {
        super();
        JScrollPane scrolltree = new JScrollPane();
        JScrollPane scrolltable = new JScrollPane();


        tree = new Tree();
        table = new Table();
        jTree = new JTree();
        jTable = new JTable();
        jTree = tree.createTree();
        jTable = table.setTable();

        /*分别设置水平和垂直滚动条自动出现
        jsp.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        */
        scrolltree.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolltree.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrolltable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolltable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        scrolltree.add(jTree);
        scrolltable.add(jTable);

        add("目录树", jTree);
//        add("目录树", tree.createTree());
        add("清单", jTable);
    }

    public JTree getGoodsTree() {
        return jTree;
    }

    public JTable getGoodsTable() {
        return jTable;
    }
}