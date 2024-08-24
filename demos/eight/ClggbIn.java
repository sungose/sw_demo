package com.echo.demos.eight;
import javax.swing.*;

class ClggbIn extends JInternalFrame implements InterfaceForm {
    public ClggbIn() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    ClggbInJPanel clrk;

    public void ExecuteForm(JMenuItem src) {
        closable = true;
        try {
            clrk = new ClggbInJPanel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
