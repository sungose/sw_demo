package com.echo.demos.four;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Toolkit;

class MapMenuItem extends JMenuItem implements ActionListener{
    String buttonFuctionClassName;
    MapMenuItem(String buttonFunctionClassName)
    {
        super();
        addActionListener(this);
        this.buttonFuctionClassName=buttonFunctionClassName;
    }

    MapMenuItem(String text, Icon icon,String buttonFunctionClassName)
    {
        super(text,icon);
        addActionListener(this);
        this.buttonFuctionClassName=buttonFunctionClassName;
    }
    MapMenuItem(String text,String buttonFunctionClassName)
    {
        super(text);
        addActionListener(this);
        this.buttonFuctionClassName=buttonFunctionClassName;
    }
    MapMenuItem(Icon icon,String buttonFunctionClassName)
    {
        super(icon);
        addActionListener(this);
        this.buttonFuctionClassName=buttonFunctionClassName;
    }
    public void actionPerformed(ActionEvent event) {
        InterfaceForm curForm;
        try{
            Class cs=Class.forName(buttonFuctionClassName);
            curForm=(InterfaceForm)cs.newInstance();
            curForm.ExecuteForm(this);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("类:("+buttonFuctionClassName+")不存在！");
        }
    }
}