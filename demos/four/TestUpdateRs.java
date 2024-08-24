package com.echo.demos.four;

import java.sql.*;
public class TestUpdateRs {
    public static void main(String args[]){
	
	try{
	    DBConnection dbc=new DBConnection();  
	    ResultSet rs=dbc.getStatement().executeQuery("select * from clggb");
	    rs.last();
	    //更新一行数据
	    rs.updateString("ggxh","AAAA");
	    rs.updateRow();   //强制后台更新
 
	    //插入新行
	    rs.moveToInsertRow();  //将光标移动到插入行
	    rs.updateString("mc","测试");
	    rs.updateString("ggxh","测试");
	    rs.updateString("dw","测");
	    rs.updateString("hh","B04");
	    rs.insertRow(); //将插入行的内容插入到此resultset对象和数据库中
	    //将光标移动到新建的行
	    rs.moveToCurrentRow();
 
	    //删除行（光标不位于插入行上时，不能调用此方法）
//	    rs.absolute(5);
//	    rs.deleteRow();
 
	    //取消更新
	    //rs.cancelRowUpdates();
 
	  }catch(SQLException e){
	    e.printStackTrace();
	  }
    }
}