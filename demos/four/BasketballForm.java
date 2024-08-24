package com.echo.demos.four;

import javax.swing.*;

class BasketballForm extends JInternalFrame implements InterfaceForm {
 public BasketballForm(){

  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 }

  public  void ExecuteForm(JMenuItem src)
 {
     try {
          MDIFrame.getDesktopPane().add(this);
          setMaximum(true);
          repaint();
          closable =true;
          setVisible(true);          
         }
     catch (Exception e) {}
 }
}