package com.echo.demos.six;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.*;

public class MDIFrame extends JFrame {
    private static JDesktopPane desktopPane;
    Container contentPane;
    JMenuBar menubar;
    JMenu menu, subMenu;
    private static DBConnection dbc;
    MapMenuItem itemLiterature, itemCooking;

    public MDIFrame() {
        this("");
    }

    public MDIFrame(String s) {
        dbc = new DBConnection();
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        init(s);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        desktopPane = new JDesktopPane();
        contentPane.add(desktopPane, BorderLayout.CENTER);

    }

    void init(String s) {
        JMenu mymenu[] = new JMenu[3];
        JMenuItem menuitem;
        int menulayer;
        String menucode; //
        String menutitle;//
        setTitle(s);
        menubar = new JMenuBar();
        mymenu[0] = new JMenu("查询");
        mymenu[1] = new JMenu("新增");
        mymenu[2] = new JMenu("修改");
        subMenu = new JMenu("新增记录");
        itemLiterature = new MapMenuItem("目录树修改", LiteratureForm.class.getName());

        mymenu[1].add(subMenu);
        mymenu[1].add(itemLiterature);

        menubar.add(mymenu[0]);
        menubar.add(mymenu[1]);
        menubar.add(mymenu[2]);
        setJMenuBar(menubar);
//       TODO menubar.
        try {
        } catch (Exception e) {
        }
        setJMenuBar(menubar);
    }

    public static JDesktopPane getDesktopPane() {
        return desktopPane;
    }

    public static DBConnection getDBConnection() {
        return dbc;
    }

    public void classdispose() {
        try {
            dbc.getStatement().close();
            dbc.getConnection().close();
        } catch (Exception e) {
        }
    }
}