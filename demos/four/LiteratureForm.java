package com.echo.demos.four;

import javax.swing.*;

public class LiteratureForm extends JInternalFrame implements InterfaceForm {
    public LiteratureForm() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void ExecuteForm(JMenuItem src) {
        closable = true;
        try {
            MDIFrame.getDesktopPane().add(this);
            setMaximum(true);
            repaint();
            setVisible(true);
        } catch (Exception e) {
        }
    }
}