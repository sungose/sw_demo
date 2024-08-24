package com.echo.utils;

import java.sql.*;
import java.util.Properties;

/**
 * JDBC工具类
 * 1.注入数据库驱动
 * 2.获取数据库连接
 * 3.关闭重要的连接资源
 */
public class JDBCUtils {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/cangku";
    private static final String USER_NAME = "sun";
    private static final String PASSWORD = "031217";


    // 2. 在静态代码块中注册驱动
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册驱动
     * @param driverName
     * @return
     * @throws ClassNotFoundException
     */
    public static void registDriver(String driverName) throws ClassNotFoundException {
        Class.forName(driverName);
    }


    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取连接的方法
     * @param url
     * @param userName
     * @param passWord
     * @return
     * @throws SQLException
     */
    public static Connection getConnection(String url, String userName, String passWord) throws SQLException {

        // Properties info=new Properties();
        // // 设置连接用户名
        // info.put("user", "root");
        // // 设置允许执行多条SQL语句
        // info.put("allowMultiQueries","true");

        // connection = DriverManager.getConnection(url, info);

        return DriverManager.getConnection(url, userName, passWord);
    }


    // 关闭资源
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // 重载关闭方法close(Connection conn, Statement stmt)
    public static void close(Connection conn, Statement stmt){
        close(conn,stmt,null);
    }
}
