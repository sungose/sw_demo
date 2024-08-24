package com.echo.demos.eight;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

// 导航条
public class DBNavigate extends JPanel implements ActionListener, INavigate {
    private ResultSet rs = null;
    private IResultSetOperate rsop = null;//结果集操作接口
    private String[] navcatption = {"First", "Previous", "Next", "Last", "Insert", "Delete", "Edit", "Cancel"};
    private JButton[] navbutton;
    private String[] result;
    private int currentRow = 0;

    public DBNavigate() {
        super();
        setPreferredSize(new Dimension(0, 60));//??????????С????Ч
        Border lineBorder = new LineBorder(Color.GRAY, 2);
        setBorder(lineBorder);
        navbutton = new JButton[navcatption.length];
        for (int i = 0; i < navcatption.length; i++) {
            navbutton[i] = new JButton(navcatption[i]);
            add(navbutton[i]);
            navbutton[i].addActionListener(this);
        }
    }

    //设置结果
    public void setResultSet(ResultSet rs) {
        try {
            this.rs = rs;
            rs.first();
            if (rsop != null)
                rsop.updateResultSetToView();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setResultSetOperate(IResultSetOperate op) {
        this.rsop = op;
        if (rsop != null)
            rsop.updateResultSetToView();
    }

    public void first() {
        try {
            if (rs != null) {
                rs.first();
                if (rsop != null)
                    rsop.updateResultSetToView();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void previous() {
        try {
            if (rs != null) {
                rs.previous();
                if (rs.isBeforeFirst()) {
                    rs.first();
                }
                if (rsop != null)
                    rsop.updateResultSetToView();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void next() {
        try {
            if (rs != null) {
                rs.next();
                if (rs.isAfterLast()) {
                    rs.last();
                }
                if (rsop != null)
                    rsop.updateResultSetToView();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void last() {
        try {
            if (rs != null) {
                rs.last();
                if (rsop != null)
                    rsop.updateResultSetToView();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insert() {
        try {
            if (rs != null) {
                rs.insertRow();
                rs.moveToInsertRow();
                if (rsop != null) {
                    rsop.updateResultSetToView();
                    rsop.enableView(true);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (rs != null) {
            if (rsop != null) {
                rsop.updateViewToResultSet();
                navbutton[6].setText("Edit");
                rsop.enableView(false);
            }
        }
    }

    public void delete() {
        try {
            if (rs != null) {
                if (rs.getRow() != 0) {
                    int ok = JOptionPane.showConfirmDialog(null, "Delete?");
                    if (ok == JOptionPane.YES_OPTION) {
                        rs.deleteRow();
                        rs.moveToCurrentRow();
                    }
                    rsop.enableView(false);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void edit() {
        if (navbutton[6].getText().contains("Save")) {
            if (rs != null && rsop != null) {
                rsop.updateViewToResultSet();
                rsop.enableView(false);
                navbutton[6].setText("Edit");
            }
        } else if (rs != null && rsop != null) {
            rsop.enableView(true);
            navbutton[6].setText("Save");
        }
    }


    public void cancel() {
        try {
            if (rs != null) {
                if (rs.getRow() != 0)
                    if (rsop != null) {
                        rsop.updateResultSetToView();
                        rsop.enableView(false);
                        navbutton[6].setText("Edit");
                    }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == navbutton[0])
            first();
        else if (e.getSource() == navbutton[1])
            previous();
        else if (e.getSource() == navbutton[2])
            next();
        else if (e.getSource() == navbutton[3])
            last();
        else if (e.getSource() == navbutton[4])
            insert();
        else if (e.getSource() == navbutton[5])
            delete();
        else if (e.getSource() == navbutton[6])
            edit();
        else
            cancel();
    }

}

