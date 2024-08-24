package com.echo.demos.eight;

import javax.swing.*;
/**
 * 测试
 */
public class Test1 {
    public static void main(String args[]){

        JFrame frame = new JFrame("新增入库");
        frame.setSize(1050,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jPanel = new JPanel();
        // 添加面板
        frame.add(jPanel);
        /*
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponent(jPanel);
        frame.setVisible(true);
    }

    private static void placeComponent(JPanel jPanel) {
        // 设置布局为null
        jPanel.setLayout(null);
        JLabel ckhhLabel = new JLabel("ckhh:");
        ckhhLabel.setBounds(10,20,80,25);
        jPanel.add(ckhhLabel);

        JTextField ckhhText = new JTextField(20);
        ckhhText.setBounds(100,20,165,25);
        jPanel.add(ckhhText);

        JLabel hhLabel = new JLabel("hh:");
        hhLabel.setBounds(10,50,80,25);
        jPanel.add(hhLabel);

        JTextField hhText = new JTextField(20);
        hhText.setBounds(100,50,165,25);
        jPanel.add(hhText);

        JLabel mcLabel = new JLabel("mc:");
        mcLabel.setBounds(10,80,80,25);
        jPanel.add(mcLabel);

        JTextField mcText = new JTextField(20);
        mcText.setBounds(100,80,165,25);
        jPanel.add(mcText);

        JLabel slLabel = new JLabel("sl:");
        slLabel.setBounds(10,110,80,25);
        jPanel.add(slLabel);

        JTextField slText = new JTextField(20);
        slText.setBounds(100,110,165,25);
        jPanel.add(slText);

        JLabel djLabel = new JLabel("dj:");
        djLabel.setBounds(10,140,80,25);
        jPanel.add(djLabel);

        JTextField djText = new JTextField(20);
        djText.setBounds(100,140,165,25);
        jPanel.add(djText);


        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */


        JButton inbound = new JButton("outbound");
        inbound.setBounds(10,170,80,25);
        jPanel.add(inbound);
    }
}
