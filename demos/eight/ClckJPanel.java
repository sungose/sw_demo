package com.echo.demos.eight;
import com.echo.dao.CltzbDao;
import com.echo.javabean.Clrk;
import com.echo.utils.ToolUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 材料出库模版
public class ClckJPanel {
    private JLabel ckbhLabel = new JLabel("仓库编号:");
    private JLabel hhLabel = new JLabel("货号:");
    private JLabel slLabel = new JLabel("出库数量:");
    private JLabel djLabel = new JLabel("出库单价:");
    private JLabel jeLabel = new JLabel("出库金额:");
    private JLabel ywlbLabel = new JLabel("业务类别:");
    Clrk clrk = new Clrk();
    JButton inbound;
    CltzbDao cltzbDao;
    JFrame frame = new JFrame("材料出库");

    Container container = frame.getContentPane();

    JTextField ckbhText = new JTextField();
    JTextField hhText = new JTextField();
    JTextField slText = new JTextField();
    JTextField djText = new JTextField();
    JTextField jeText = new JTextField();
    JTextField ywlbText = new JTextField();

    public ClckJPanel(){
        ToolUtils.getCenter(frame,900,600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        container.setLayout(new BorderLayout());
        setJPanel();
        frame.setVisible(true);
    }

    public void setJPanel(){
        // 设置布局为null
        JPanel jPanel = new JPanel();
        // 添加面板
        jPanel.setLayout(null);

        ckbhLabel.setBounds(10,20,80,25);
        jPanel.add(ckbhLabel);


        ckbhText.setBounds(100,20,165,25);
        jPanel.add(ckbhText);


        hhLabel.setBounds(10,50,80,25);
        jPanel.add(hhLabel);


        hhText.setBounds(100,50,165,25);
        jPanel.add(hhText);


        slLabel.setBounds(10,80,80,25);
        jPanel.add(slLabel);

        slText.setBounds(100,80,165,25);
        jPanel.add(slText);


        djLabel.setBounds(10,110,80,25);
        jPanel.add(djLabel);

        djText.setBounds(100,110,165,25);
        jPanel.add(djText);


        jeLabel.setBounds(10,140,80,25);
        jPanel.add(jeLabel);

        jeText.setBounds(100,140,165,25);
        jPanel.add(jeText);


        ywlbLabel.setBounds(10,170,80,25);
        jPanel.add(ywlbLabel);

        ywlbText.setBounds(100,170,165,25);
        jPanel.add(ywlbText);

        container.add(jPanel,"Center");


        inbound = new JButton("确认出库");
        inbound.setBounds(10,200,100,25);
        jPanel.add(inbound);

        inbound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cltzbDao = new CltzbDao();
                int i = 0;
                clrk.setCkbh(ckbhText.getText());
                clrk.setHh(hhText.getText());
                clrk.setSl(Integer.valueOf( slText.getText()));
                clrk.setDj(Double.valueOf(djText.getText()));
                clrk.setJe(Double.valueOf(jeText.getText()));
                clrk.setYwlb(ywlbText.getText());

                if("".equals(ckbhText.getText())){
                    // 文本框为空时
                    JOptionPane.showOptionDialog(jPanel, "您还没有输入仓库编号", "提示"
                            , JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                    return;
                }

                if("".equals(hhText.getText())){
                    // 文本框为空时
                    JOptionPane.showOptionDialog(jPanel, "您还没有输入货号", "提示"
                            , JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                    return;
                }

                int userOption =  JOptionPane.showConfirmDialog(null,"是否确认出库？","提示",JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE);	//确认对话框
                if (userOption == JOptionPane.OK_OPTION) {
                    System.err.println("是");
                    try {
                        i = cltzbDao.clrkInsert(clrk);
                        if (i == 1){
                            JOptionPane.showMessageDialog(null,"出库成功！");
                        }else {
                            JOptionPane.showMessageDialog(null, "出库失败！");
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    ckbhText.setText("");
                    hhText.setText("");
                    slText.setText("");
                    djText.setText("");
                    jeText.setText("");
                    ywlbText.setText("");
                }else {
                    System.out.println("否");
                }
            }

        });
    }

}

