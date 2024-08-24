package com.echo.demos.eight;

import javax.swing.*;

public class LiteratureForm3 extends JInternalFrame implements InterfaceForm {
    public LiteratureForm3() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    JFrame frame;


    public void ExecuteForm(JMenuItem src) {
        closable = true;
        try {
            frame = new JFrame("材料规格表");
            TableListApp tableListApp = new TableListApp();


        } catch (Exception e) {
        }
    }
}
