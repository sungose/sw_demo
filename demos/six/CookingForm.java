package com.echo.demos.six;

import javax.swing.*;

class CookingForm extends CardTemplateFrame implements InterfaceForm {
    public CookingForm() {
        setBounds(20, 20, 100, 100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void ExecuteForm(JMenuItem src) {
        setTitle(src.getText());
    }
}