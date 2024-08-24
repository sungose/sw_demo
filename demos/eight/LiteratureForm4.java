package com.echo.demos.eight;
import com.echo.utils.JDBCUtils;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

/**
 * 材料规格信息
 */

public class LiteratureForm4 extends CardTemplateFrame implements InterfaceForm, IResultSetOperate {
    private int id_key;
    private ResultSet ggb_res;
    private JTextField hh;
    private JTextField mc;
    private JTextField ggxh;
    private JTextField dw;
    private JFormattedTextField sl;
    private JFormattedTextField dj;
    private JFormattedTextField je;

    public LiteratureForm4() {
        setTitle("详情");
        maximizable = true;
        resizable = true;
        closable = true;
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void ExecuteForm(JMenuItem src) {
        try {
            MDIFrame2.getDesktopPane().add(this);
            setMaximum(true);
            init();
            repaint();

        } catch (Exception e) {
        }
    }

    private void init() {
        try {
//            ggb_res = MDIFrame.getDBConnection().getStatement().executeQuery("select * from clggb order by hh");
            ggb_res = JDBCUtils.getConnection().prepareStatement("select * from clggb order by hh",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cardpanel.addTitle("hh");
        cardpanel.addTitle("mc");
        cardpanel.addTitle("ggxh");
        cardpanel.addTitle("dw");
        cardpanel.addTitle("kcs");
        cardpanel.addTitle("pjj");
        cardpanel.addTitle("kczj");
        hh = new JTextField(10);
        mc = new JTextField(24);
        ggxh = new JTextField(24);
        dw = new JTextField(6);
        NumberFormat principleFormat = NumberFormat.getNumberInstance();
        sl = new JFormattedTextField(principleFormat);
        dj = new JFormattedTextField(principleFormat);
        je = new JFormattedTextField(principleFormat);
        cardpanel.addEditComponent(hh);
        cardpanel.addEditComponent(mc);
        cardpanel.addEditComponent(ggxh);
        cardpanel.addEditComponent(dw);
        cardpanel.addEditComponent(sl);
        cardpanel.addEditComponent(dj);
        cardpanel.addEditComponent(je);
        dbpanel.setResultSet(ggb_res);
        dbpanel.setResultSetOperate(this);
        updateResultSetToView();
        enableView(false);
    }

    public void updateResultSetToView() {
        try {
            if (ggb_res.getRow() != 0) {
//     id_key=ggb_res.getInteger("id");
                hh.setText(ggb_res.getString("hh"));
                mc.setText(ggb_res.getString("mc"));
                ggxh.setText(ggb_res.getString("ggxh"));
                dw.setText(ggb_res.getString("dw"));
                sl.setText("" + ggb_res.getFloat("kcs"));
                dj.setText("" + ggb_res.getFloat("pjj"));
                je.setText("" + ggb_res.getFloat("kczj"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateViewToResultSet() {
        try {
            if (ggb_res.getRow() != 0) {
                ggb_res.updateString("hh", hh.getText());
                ggb_res.updateString("mc", mc.getText());
                ggb_res.updateString("ggxh", ggxh.getText());
                ggb_res.updateString("dw", dw.getText());
                ggb_res.updateFloat("kcs", Float.parseFloat(sl.getText()));
                ggb_res.updateFloat("pjj", Float.parseFloat(dj.getText()));
                ggb_res.updateFloat("kczj", Float.parseFloat(je.getText()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void enableView(boolean enable) {
        hh.setEnabled(enable);
        mc.setEnabled(enable);
        ggxh.setEnabled(enable);
        dw.setEnabled(enable);
        sl.setEnabled(enable);
        dj.setEnabled(enable);
        je.setEnabled(enable);
    }

}