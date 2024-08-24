package com.echo.demos.six;

import javax.swing.*;

class BasketballForm extends CardTemplateFrame implements InterfaceForm {
 public BasketballForm(){
  setBounds(10,10,100,100); 
  setVisible(true);
  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 }

  public  void ExecuteForm(JMenuItem src)
 {
  src.getRootPane().getParent();
  setTitle(src.getText());
 }
}