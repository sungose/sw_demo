package com.echo.demos.eight;

import com.echo.dao.ClggbDao;
import com.echo.dao.CltzbDao;
import com.echo.javabean.Clgg;
import com.echo.javabean.Clll;
import com.echo.utils.ToolUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

// 材料领用模版
public class CllyJPanel {
    private JFrame frame;
    CltzbDao cltzbDao;
    Clll clll;
    private JLabel hhLabel = new JLabel("材料货号:");
    private JLabel ckbhLabel = new JLabel("仓库编号:");
    private JLabel slLabel = new JLabel("领用数量:");
    private JLabel lysjLabel = new JLabel("领用时间:");
    private JLabel lyrLabel = new JLabel("领用人:");
    private JLabel statusLabel = new JLabel("领用状态:");
    private JTextField hhText = new JTextField(20);
    private JTextField ckbhText = new JTextField(20);
    private JTextField slText = new JTextField(20);
    private JTextField lysjText = new JTextField(20);
    private JTextField lyrText = new JTextField(20);
    private JTextField statusText = new JTextField(20);

    public CllyJPanel(){
        frame = new JFrame("材料领料");
        Container container = frame.getContentPane();
        ToolUtils.getCenter(frame, 900, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel jPanel = new JPanel();
        frame.add(jPanel);
        setJPanel(jPanel);
    }

    public void setJPanel(JPanel jPanel){
        // 设置布局为null
        jPanel.setLayout(null);
        hhLabel.setBounds(10, 50, 80, 25);
        jPanel.add(hhLabel);
        hhText.setBounds(100, 50, 165, 25);
        jPanel.add(hhText);

        ckbhLabel.setBounds(10, 80, 80, 25);
        jPanel.add(ckbhLabel);
        ckbhText.setBounds(100, 80, 165, 25);
        jPanel.add(ckbhText);

        slLabel.setBounds(10, 110, 80, 25);
        jPanel.add(slLabel);
        slText.setBounds(100, 110, 165, 25);
        jPanel.add(slText);

        lysjLabel.setBounds(10, 140, 80, 25);
        jPanel.add(lysjLabel);
        lysjText.setBounds(100, 140, 165, 25);
        jPanel.add(lysjText);

        lyrLabel.setBounds(10, 170, 80, 25);
        jPanel.add(lyrLabel);
        lyrText.setBounds(100, 170, 165, 25);
        jPanel.add(lyrText);

        statusLabel.setBounds(10, 210, 80, 25);
        jPanel.add(statusLabel);
        statusText.setBounds(100, 210, 165, 25);
        jPanel.add(statusText);

        JButton inbound = new JButton("确认领用");
        inbound.setBounds(80, 240, 100, 25);
        jPanel.add(inbound);

        inbound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cltzbDao = new CltzbDao();
                int i = 0;
                clll = new Clll();
                clll.setHh(hhText.getText());
                clll.setCkbh(ckbhText.getText());
                clll.setSl(Integer.valueOf(slText.getText()));
                clll.setLysj(Date.valueOf(lysjText.getText()));
                clll.setRecipient(lyrText.getText());
                clll.setStstus(statusText.getText());

                if("".equals(ckbhText.getText())){
                    // 文本框为空时
                    JOptionPane.showOptionDialog(jPanel, "您还没有仓库编号", "提示"
                            , JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                    return;
                }

                if("".equals(hhText.getText())){
                    // 文本框为空时
                    JOptionPane.showOptionDialog(jPanel, "您还没有输入货号", "提示"
                            , JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                    return;
                }

                int userOption =  JOptionPane.showConfirmDialog(null,"是否确认新增该材料型号？","提示",JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE);	//确认对话框
                if (userOption == JOptionPane.OK_OPTION) {
                    System.err.println("是");
                    try {
                        i = cltzbDao.clllInsert(clll);
                        if (i == 1){
                            JOptionPane.showMessageDialog(null,"领用材料成功！");
                        }else {
                            JOptionPane.showMessageDialog(null,"领用材料失败！");
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    hhText.setText("");
                    ckbhText.setText("");
                    slText.setText("");
                    lysjText.setText("");
                    lyrText.setText("");
                    statusText.setText("");
                }else {
                    System.out.println("否");
                }
            }
        });
    }


}
