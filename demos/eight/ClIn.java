package com.echo.demos.eight;
import javax.swing.*;

public class ClIn extends JInternalFrame implements InterfaceForm {
    public ClIn() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    ClrkJPanel clrkJPanel;

    public void ExecuteForm(JMenuItem src) {
        closable = true;
        try {
            clrkJPanel = new ClrkJPanel();
        } catch (Exception e) {

        }

    }
}
