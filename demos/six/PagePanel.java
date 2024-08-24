package com.echo.demos.six;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;

public class PagePanel extends JTabbedPane {
    private JTree goodstree;
    private JTable goodstable;

    public PagePanel() {
        super();
        JScrollPane scrolltree = new JScrollPane();
        JScrollPane scrolltable = new JScrollPane();
        goodstree = new JTree();
        goodstable = new JTable();
        goodstree.setModel(null);
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
        scrolltree.add(goodstree);
        scrolltable.add(goodstable);
        add("目录树", scrolltree);
        add("清单", scrolltable);
    }

    public JTree getGoodsTree() {
        return goodstree;
    }

    public JTable getGoodsTable() {
        return goodstable;
    }
}