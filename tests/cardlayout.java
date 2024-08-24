package com.echo.tests;


import com.echo.services.ClggbDao;
import com.echo.utils.ToolUtils;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;//引入事件包

import static com.sun.org.apache.xerces.internal.util.DOMUtil.setVisible;


//定义类时实现监听接口

public class cardlayout extends JFrame implements ActionListener {

    JButton nextbutton;

    JButton preButton;



    Panel cardPanel = new Panel();

    Panel controlpaPanel = new Panel();

    //定义卡片布局对象

    CardLayout card = new CardLayout();

    ClggbDao clggb = new ClggbDao();

    //定义构造函数

    public cardlayout() {

//        JFrame win = new JFrame("卡片布局管理器");

//        win.super("卡片布局管理器");
//
//        win.setSize(300, 200);
//
//        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        win.setLocationRelativeTo(null);
//
//        win.setVisible(true);

//        super("卡片布局管理器");
//
//        setSize(300, 200);
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        setLocationRelativeTo(null);
//
//        setVisible(true);
        ToolUtils.getCenter(this,900,600);


        //设置cardPanel面板对象为卡片布局

        cardPanel.setLayout(card);


        //循环，在cardPanel面板对象中添加五个按钮

        //因为cardPanel面板对象为卡片布局，因此只显示最先添加的组件

        for (int i = 0; i < 5; i++) {
            String j = String.valueOf(i);
            int num1 = (int)(Math.random()*1251);
            String[][] arr = clggb.selectClggb(num1-1,1);
            cardPanel.add(new JButton(i+":"+"clggb第"+num1+"条列表的数据为：" + "mc:" + arr[0][0] + " ggxh:" + arr[0][1] + " hh:" + arr[0][2] + " dw:" + arr[0][3] + " kcs:" + arr[0][4] + " pjj:" + arr[0][5] + " kczj:" + arr[0][6]));

        }

        //实例化按钮对象

        nextbutton = new JButton("下一张卡片");

        preButton = new JButton("上一张卡片");


        //为按钮对象注册监听器

        nextbutton.addActionListener(this);

        preButton.addActionListener(this);


        controlpaPanel.add(preButton);

        controlpaPanel.add(nextbutton);


        //定义容器对象为当前窗体容器对象

        Container container = getContentPane();


        //将 cardPanel面板放置在窗口边界布局的中间，窗口默认为边界布局

        container.add(cardPanel, BorderLayout.CENTER);

        // 将controlpaPanel面板放置在窗口边界布局的南边，

        container.add(controlpaPanel, BorderLayout.SOUTH);

    }


    //实现按钮的监听触发时的处理

    public void actionPerformed(ActionEvent e) {

        //如果用户单击nextbutton，执行的语句

        if (e.getSource() == nextbutton) {

            //切换cardPanel面板中当前组件之后的一个组件

            //若当前组件为最后添加的组件，则显示第一个组件，即卡片组件显示是循环的。

            card.next(cardPanel);

        }

        if (e.getSource() == preButton) {

            //切换cardPanel面板中当前组件之前的一个组件

            //若当前组件为第一个添加的组件，则显示最后一个组件，即卡片组件显示是循环的。

            card.previous(cardPanel);

        }

    }

    public static void main(String[] args) {

        cardlayout kapian = new cardlayout();

    }

}