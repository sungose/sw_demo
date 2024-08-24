package com.echo.demos.six;

import java.sql.*;
import java.io.File;
import java.io.IOException;
//==============================================================================
// 本类负责数据库的连接操作，在进行本类对象实例化时自动进行数据库的连接处理
//==============================================================================
public class DBConnection {
	private Connection conn = null ;
	private Statement stmt = null;

	//---------------------------------------------------------
	// 在构造方法调用时将进行数据库连接对象的取得
	//----------------------------------------------------------
	public DBConnection() {
		String dbDriver;
		String dbUrl;
		String dbUser ;
		String dbPassword;
		dbDriver="com.mysql.cj.jdbc.Driver";;
		dbUrl="jdbc:mysql://localhost:3306/clggb";;
		dbUser="sun";
		dbPassword="031217";
		try {
			Class.forName(dbDriver) ;	// 加载数据库驱动程序
			this.conn = DriverManager.getConnection(dbUrl,dbUser,dbPassword) ;
//   	           this.stmt=conn.createStatement(); //创建Statement对象
			this.stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); //创建Statement对象
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	//--------------------------------------------------------------
	//取得数据库连接对象
	// @return 实例化的Connection对象，如果返回null，表示没有连接成功
	//--------------------------------------------------------------
	public Connection getConnection() {
		return this.conn ;
	}

	public Statement getStatement() {
		return this.stmt;
	}
	//--------------------------------------------------------------
	// 进行数据库的关闭操作
	//--------------------------------------------------------------
	public void close() {
		if (this.conn != null) {
			try {
				this.stmt.close();
				this.conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}