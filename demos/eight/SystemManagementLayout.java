package com.echo.demos.eight;

import javax.swing.*;
import java.awt.*;

public class SystemManagementLayout {

    public static void main(String[] args) {
        // 创建主窗口  
        JFrame frame = new JFrame("系统管理");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1050, 900); // 设置窗口大小
        frame.setLayout(new BorderLayout()); // 使用边框布局  

        // 创建菜单栏  
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // 创建菜单项  
        JMenu fileMenu = new JMenu("文件");
        JMenu editMenu = new JMenu("编辑");
        JMenu viewMenu = new JMenu("查看");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);

        // 创建标签页面板  
        JTabbedPane tabbedPane = new JTabbedPane();
        // 添加一个标签页示例  
        tabbedPane.addTab("用户详情", new UserDetailsPanel());
        tabbedPane.addTab("用户添加", new JPanel());
        // 你可以根据需要添加更多的标签页

        // 将标签页面板添加到主窗口中央  
        frame.add(tabbedPane, BorderLayout.CENTER);

        // 显示窗口  
        frame.setVisible(true);
    }

    
    // TODO 学习这种思路，当一个ui复杂后，需要单独使用一个类来进行维护
    static class UserDetailsPanel extends JPanel {
        public UserDetailsPanel() {
            // 设置面板的布局
            setLayout(new GridLayout(7, 2, 5, 5));

            // 添加用户详情的标签和文本框
            add(new JLabel("用户名:"));
            JTextField usernameField = new JTextField("张三");
            add(usernameField);

            add(new JLabel("邮箱:"));
            JTextField emailField = new JTextField("zhangsan@example.com");
            add(emailField);

            add(new JLabel("电话:"));
            JTextField phoneField = new JTextField("13800000000");
            add(phoneField);

            add(new JLabel("电话:"));
            JTextField phoneField1 = new JTextField("13800000000");
            add(phoneField1);

            add(new JLabel("电话:"));
            JTextField phoneField2 = new JTextField("13800000000");
            add(phoneField2);

            add(new JLabel("电话:"));
            JTextField phoneField3 = new JTextField("13800000000");
            add(phoneField3);

            add(new JLabel("电话:"));
            JTextField phoneField4 = new JTextField("13800000000");
            add(phoneField4);
        }
    }
}