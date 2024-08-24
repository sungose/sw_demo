package com.echo.demos.eight;

import javax.swing.*;

class ClggbOut extends JInternalFrame implements InterfaceForm {
    public ClggbOut() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    ClggbOutJPanel clck;

    public void ExecuteForm(JMenuItem src) {
        closable = true;
        try {
            clck = new ClggbOutJPanel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
