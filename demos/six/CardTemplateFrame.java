package com.echo.demos.six;

import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;

public class CardTemplateFrame extends JInternalFrame {//JInternalFrame
    CardPanel cardpanel;    //卡片输入面板部分
    DBNavigate dbpanel; //
    PagePanel tabpanel;

    public CardTemplateFrame() {
        super();
//        setLayout(new BorderLayout());
        tabpanel = new PagePanel();
        cardpanel = new CardPanel();
        dbpanel = new DBNavigate();
        add(dbpanel, BorderLayout.SOUTH);
        add(cardpanel, BorderLayout.WEST);
        add(tabpanel, BorderLayout.CENTER);
        //  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        maximizable = true;
        resizable = true;
        closable = true;
// 设置窗体最大化
//   setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        validate();
    }

    public CardTemplateFrame(String title) {
        super(title);
    }
}