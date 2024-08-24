package com.echo.demos.four;

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

    MapMenuItem itemLiterature,itemLiterature2, itemCooking;

    public MDIFrame() {
        this("");
    }

    public MDIFrame(String s) {

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
        menubar = new JMenuBar();
        menu = new JMenu("菜单");
        subMenu = new JMenu("体育话题");
        itemLiterature = new MapMenuItem("文学话题", new ImageIcon("a.gif"), LiteratureForm.class.getName());
        itemLiterature2 = new MapMenuItem("数据库查询", new ImageIcon("a.gif"), LiteratureForm2.class.getName());
        itemCooking = new MapMenuItem("烹饪话题", new ImageIcon("b.gif"), CookingForm.class.getName());
        menu.add(itemLiterature);
        menu.add(itemLiterature2);
        menu.addSeparator();   //在菜单之间增加分隔线
        menu.add(itemCooking);
        menu.add(subMenu);
        subMenu.add(new MapMenuItem("足球", new ImageIcon("c.gif"), FootballForm.class.getName()));
        subMenu.add(new MapMenuItem("篮球", new ImageIcon("d.gif"), BasketballForm.class.getName()));
        menubar.add(menu);
        setJMenuBar(menubar);
    }

    public static JDesktopPane getDesktopPane() {
        return desktopPane;
    }

}