package com.echo.demos.eight;

import javax.swing.*;

public class LiteratureForm extends JInternalFrame implements InterfaceForm {
    public LiteratureForm() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    JFrame frame;


    public void ExecuteForm(JMenuItem src) {
        closable = true;
        try {
            frame = new JFrame("材料目录");
            Tree_View myTree = new Tree_View();

        } catch (Exception e) {
        }
    }
}
