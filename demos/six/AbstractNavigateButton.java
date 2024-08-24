package com.echo.demos.six;

import javax.swing.*;
import java.sql.*;
public class AbstractNavigateButton extends JButton{
    static public  ResultSet          rs=null;
    static public  boolean            lock=false;
//    static public  ResultSetOperate   rsop=null;//结果集操作接口
    void setResultset(ResultSet _rs){
        rs=_rs;
    }
//    void setResultSetOperate(ResultSetOperate _op){
//        rsop=_op;
//    }
}