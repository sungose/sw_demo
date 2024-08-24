package com.echo.demos.eight;
import javax.swing.*;

class Clll extends JInternalFrame implements InterfaceForm {
    public Clll() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    CllyJPanel clly;

    public void ExecuteForm(JMenuItem src) {
        closable = true;
        try {
            clly = new CllyJPanel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
