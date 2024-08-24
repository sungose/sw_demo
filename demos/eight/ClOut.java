package com.echo.demos.eight;
import javax.swing.*;

public class ClOut extends JInternalFrame implements InterfaceForm {
    public ClOut() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    ClckJPanel clckJPanel;

    public void ExecuteForm(JMenuItem src) {
        closable = true;
        try {
            clckJPanel = new ClckJPanel();
        } catch (Exception e) {

        }

    }
}
