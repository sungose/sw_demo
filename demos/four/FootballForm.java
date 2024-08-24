package com.echo.demos.four;

import javax.swing.*;

public class FootballForm extends JInternalFrame implements InterfaceForm {
    public FootballForm() {
        closable = true;
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void ExecuteForm(JMenuItem src) {
        try {
            MDIFrame.getDesktopPane().add(this);
            setMaximum(true);
            repaint();
        } catch (Exception e) {
        }
    }
}