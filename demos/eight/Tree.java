package com.echo.demos.eight;

import com.echo.utils.JDBCUtils;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Tree implements MouseListener {
    JTree myTree;
    JScrollPane leftPane;
    ResultSet rs = null;
    Container container;
    public JTree createTree() {
        container = new Container();
        container.setLayout(new BorderLayout());
        DefaultMutableTreeNode[] aNode = new DefaultMutableTreeNode[7];
        String curHH = "", curMC;
        int dotCount = 0;
        try {
            //查询表
            rs = JDBCUtils.getConnection().prepareStatement("select * from clggb order by hh").executeQuery();
//				rs.first();
            while (rs.next()) {      //显示所有记录
                curHH = rs.getString("hh").trim();
                curMC = rs.getString("mc").trim();
                dotCount = curHH.length() / 2;
                System.out.println(dotCount + "=" + curHH + ":" + curMC + "\n");
                if (dotCount == 0)
                    aNode[0] = new DefaultMutableTreeNode(curHH + ":" + curMC);
                else {
                    aNode[dotCount] = new DefaultMutableTreeNode(curHH + ":" + curMC);
                    aNode[dotCount - 1].add(aNode[dotCount]);
                }

            }
        } catch (Exception e) {
            System.err.println("数据库出错");
            e.printStackTrace();
            System.exit(1);  // terminate program
        }
        myTree = new JTree(aNode[0]);
        myTree.setEditable(true);
        myTree.getSelectionModel().setSelectionMode(javax.swing.tree.TreeSelectionModel.SINGLE_TREE_SELECTION);
        myTree.addMouseListener(this);

        myTree.getCellEditor().addCellEditorListener(
                new MyTreeCellEditorListener(myTree));

        leftPane = new JScrollPane();//myTree
        leftPane.setViewportView(myTree);
        return myTree;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
