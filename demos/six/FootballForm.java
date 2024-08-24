package com.echo.demos.six;

import javax.swing.*;

class FootballForm extends CardTemplateFrame implements InterfaceForm {
    public FootballForm() {
        setBounds(20, 20, 100, 100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void ExecuteForm(JMenuItem src) {
        setTitle(src.getText());
    }
}