package com.echo.demos.eight;

import javax.swing.*;
import java.awt.*;

public class MDIFrame2 extends JFrame {
    private static JDesktopPane desktopPane;
    JMenuBar menubar;
    JMenu jMenu[] = new JMenu[6];

    MapMenuItem itemLiterature,itemLiterature2,itemLiterature3,itemLiterature4;

    public MDIFrame2() {
        this("");
    }

    public MDIFrame2(String s) {

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        init(s);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(800,600));
        this.pack();
        setResizable(true);
        setVisible(true);
        desktopPane = new JDesktopPane();
        contentPane.add(desktopPane, BorderLayout.CENTER);
    }

    void init(String s) {
        menubar = new JMenuBar();  // 菜单栏

        jMenu[1] = new JMenu("查询");
        jMenu[2] = new JMenu("入库");
        jMenu[3] = new JMenu("出库");
        jMenu[4] = new JMenu("修改");
        jMenu[0] = new JMenu("仓库信息管理");

        itemLiterature = new MapMenuItem("目录树修改",  LiteratureForm.class.getName());
        itemLiterature2 = new MapMenuItem("材料规格表查询",  LiteratureForm2.class.getName());
        itemLiterature3 = new MapMenuItem("材料规格表浏览",  LiteratureForm3.class.getName());
        itemLiterature4 = new MapMenuItem("材料规格信息",  LiteratureForm4.class.getName());

        jMenu[1].add(itemLiterature3);
        jMenu[1].addSeparator();  // 分割线
        jMenu[1].add(itemLiterature2);
        jMenu[1].addSeparator();  // 分割线
        jMenu[1].add(itemLiterature4);

        jMenu[2].add(new JMenuItem("进货单审核"));
        jMenu[2].addSeparator();
        jMenu[2].add(new MapMenuItem("领料单填写",Clll.class.getName()));
        jMenu[2].addSeparator();
        jMenu[2].add(new MapMenuItem("领料单审核",ClllSelect.class.getName()));
        jMenu[2].addSeparator();
        jMenu[2].add(new MapMenuItem("材料入库",  ClIn.class.getName()));
        jMenu[2].addSeparator();
        jMenu[2].add(new MapMenuItem("材料进货",  ClggbIn.class.getName()));
        jMenu[2].addSeparator();
        jMenu[2].add(new MapMenuItem("入库记录查询",ClrkSelect.class.getName()));

        jMenu[3].add(new JMenuItem("退货单审核"));
        jMenu[3].addSeparator();
        jMenu[3].add(new JMenuItem("退料单审核"));
        jMenu[3].addSeparator();
        jMenu[3].add(new MapMenuItem("材料出库",  ClOut.class.getName()));
        jMenu[3].addSeparator();
        jMenu[3].add(new MapMenuItem("材料退货",  ClggbOut.class.getName()));
        jMenu[3].addSeparator();
        jMenu[3].add(new MapMenuItem("台账表记录查询",ClckSelect.class.getName()));

        jMenu[4].add(itemLiterature);
        jMenu[4].addSeparator();
        jMenu[4].add(new JMenuItem("仓库盘点"));

        jMenu[0].add(new MapMenuItem("用户信息",UserSelect.class.getName()));
        jMenu[0].add(new MapMenuItem("本仓信息",ClbcSelect.class.getName()));
        jMenu[0].addSeparator();
        jMenu[0].add(new JMenuItem("本仓管理员信息"));
        jMenu[0].add(new MapMenuItem("总仓信息",ClzcSelect.class.getName()));


        menubar.add(jMenu[1]);
        menubar.add(jMenu[2]);
        menubar.add(jMenu[3]);
        menubar.add(jMenu[4]);
        menubar.add(jMenu[0]);
        setJMenuBar(menubar);
    }

    public static JDesktopPane getDesktopPane() {
        return desktopPane;
    }

}