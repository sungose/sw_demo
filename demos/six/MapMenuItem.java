package com.echo.demos.six;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.lang.reflect.*;

class MapMenuItem extends JMenuItem implements ActionListener {
    String buttonFuctionClassName;

    MapMenuItem(String buttonFunctionClassName) {
        super();
        addActionListener(this);
        this.buttonFuctionClassName = buttonFunctionClassName;
    }

    MapMenuItem(String text, Icon icon, String buttonFunctionClassName) {
        super(text, icon);
        addActionListener(this);
        this.buttonFuctionClassName = buttonFunctionClassName;
    }

    MapMenuItem(String text, String buttonFunctionClassName) {
        super(text);
        addActionListener(this);
        this.buttonFuctionClassName = buttonFunctionClassName;
    }

    MapMenuItem(Icon icon, String buttonFunctionClassName) {
        super(icon);
        addActionListener(this);
        this.buttonFuctionClassName = buttonFunctionClassName;
    }

    public void actionPerformed(ActionEvent event) {
        InterfaceForm curForm;
        JDesktopPane desktopPane;
        try {
//            Class cs=Class.forName(buttonFuctionClassName);
            Class<?> cs = Class.forName(buttonFuctionClassName);
//public Student(String name, int age, String address)
//Constructor<T> getConstructor (Class<?>... parameterTypes)
//           Constructor<?> con = c.getConstructor(String.class, int.class, String.class); //�������Ĺ��췽��
//           Object obj = con.newInstance("����ϼ", 30, "����");//
            Constructor<?> con = cs.getConstructor(); //
            //  curForm=(InterfaceForm)cs.newInstance();
            curForm = (InterfaceForm) con.newInstance(); //
            curForm.ExecuteForm(this);
        } catch (Exception e) {
            System.out.println("error:(" + buttonFuctionClassName + ")");
        }
    }
}