
package com.echo.demos.eight;
import com.echo.services.ClggbDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 材料规格表查询窗口
 * 子窗口的具体实现类，就是JInternalFrame类
 */


public class LiteratureForm2 extends JInternalFrame implements InterfaceForm {
    ClggbDao test = new ClggbDao();

    public LiteratureForm2() {
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void ExecuteForm(JMenuItem src) {
        closable = true;

        try {
            // 拿到主窗口的JDesktopPane对象，使用该对象将子窗口加入其中
            MDIFrame2.getDesktopPane().add(this);


            // 拿到JInternalFrame的ContentPane 内容面板
            Container contentPane = this.getContentPane();
//            int total=test.selectCount(); 放在这里只能查询一次记录，得到的count值是固定的
            contentPane.setLayout(new BorderLayout());


            // 创建一个按钮
            JButton button = new JButton("查询clggb总记录数");
            JButton buttonClggbList = new JButton("随机查询clggb表中一条记录");

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    Test test = new Test(); 不需重复创建对象
                    int total=test.selectCount();
                    JOptionPane.showMessageDialog(contentPane, "clggb的总记录数为：" + total);
                }
            });

            // 第二个按钮
            buttonClggbList.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    生成随机数并传递给num1
                    int num1 = (int)(Math.random()*1251);
                    String[][] arr = test.selectClggb(num1-1,1);
                    JOptionPane.showMessageDialog(contentPane, "clggb第"+num1+"条列表的数据为：" + "名称:" + arr[0][0] + " 规格型号:" + arr[0][1] + " 货号:" + arr[0][2] + " 单位:" + arr[0][3] + " 库存数:" + arr[0][4] + " 平均价:" + arr[0][5] + " 库存总价:" + arr[0][6]);
                    //
                }
            });

            // 向ContentPane中加入按钮，并显示出按钮的定位
            contentPane.add(button,BorderLayout.BEFORE_FIRST_LINE);
            contentPane.add(buttonClggbList,BorderLayout.AFTER_LAST_LINE);

//            JPanel jp = new JPanel();
//            jp.setLayout(null);
//            button.setBounds(10,20,100,30);
//            buttonClggbList.setBounds(10,80,100,30);
//            contentPane.add(jp);
//            contentPane.add(button);
//            contentPane.add(buttonClggbList);

            setMaximum(true);
            repaint();
            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}