package com.echo.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.echo.utils.JDBCUtils;

public class ClggbDao {

    //
    // 1.拿到数据库连接 Conection
    // 2.
    // 3.事务成功后就提交，事务失败就回滚

    public void select(String id) {
        // 获取链接
        Connection con = JDBCUtils.getConnection();
        PreparedStatement pstmt1 = null;
        ResultSet resultSet = null;
        String selectSQL = "select `mc`, `ggxh`, `hh`, `dw`, `kcs`, `pjj`, `kczj` from `clggb` where hh=?";  //查询本仓数据                        ', '                              ', 'B                 ', '      ', 0.00, 0.00, 0.00);
        try {
            pstmt1 = con.prepareStatement(selectSQL);
            pstmt1.setString(1, id);
            // 执行查询语句并获取结果
            resultSet = pstmt1.executeQuery();
            while (resultSet.next()) {
                // 获取每个结果的列值
                String mc = resultSet.getString("mc");
                String ggxh = resultSet.getString("ggxh");
                String hh = resultSet.getString("hh");
                String dw = resultSet.getString("dw");
                int kcs = resultSet.getInt("kcs");
                int pjj = resultSet.getInt("pjj");
                int kczj = resultSet.getInt("kczj");

                // 处理结果
                //打印数据信息
                System.out.println("mc: " + mc + ", ggxh: " + ggxh + ",hh:" + hh + ",dw:" + dw + ",kcs" + kcs + ",pjj:" + pjj + ",kczj:" + kczj);
            }
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } finally {
            // 最后关闭连接资源
            JDBCUtils.close(con, null, null);

            // 关闭statement资源
            JDBCUtils.close(null, pstmt1, null);
            // 关闭reultset资源
            JDBCUtils.close(null, null, resultSet);
        }


    }

    public boolean selectid(String id) {
        // 获取链接
        Connection con = JDBCUtils.getConnection();
        PreparedStatement pstmt1 = null;
        ResultSet resultSet = null;
        boolean n = false;
        String selectSQL = "select `hh` from `clggb` where hh=?";  //查询本仓数据                        ', '                              ', 'B                 ', '      ', 0.00, 0.00, 0.00);
        try {
            pstmt1 = con.prepareStatement(selectSQL);
            // 执行查询语句并获取结果
            pstmt1.setString(1, id);
            resultSet = pstmt1.executeQuery();
            String hhid = null;
            while (resultSet.next()) {
                hhid = resultSet.getString("hh");
            }
            if (id == hhid) {
                n = true;
            } else return n ;
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } finally {
            // 最后关闭连接资源
            JDBCUtils.close(con, pstmt1, resultSet);
        }
        return n;
    }

    // 查询clggb表全部数据
    public void selectClggball() {
        // 获取链接
        Connection con = JDBCUtils.getConnection();
        PreparedStatement pstmt1 = null;
        ResultSet resultSet = null;
        String selectSQL = "select `mc`, `ggxh`, `hh`, `dw`, `kcs`, `pjj`, `kczj` from `clggb`  where 1=? ORDER BY `hh` ASC";  //查询本仓数据                        ', '                              ', 'B                 ', '      ', 0.00, 0.00, 0.00);
        try {
            pstmt1 = con.prepareStatement(selectSQL);
            pstmt1.setInt(1, 1);  //为了对应设置SQL语句中的参数值
            // 执行查询语句并获取结果
            resultSet = pstmt1.executeQuery();
            while (resultSet.next()) {
                // 获取每个结果的列值
                String mc = resultSet.getString("mc");
                String ggxh = resultSet.getString("ggxh");
                String hh = resultSet.getString("hh");
                String dw = resultSet.getString("dw");
                int kcs = resultSet.getInt("kcs");
                int pjj = resultSet.getInt("pjj");
                int kczj = resultSet.getInt("kczj");

                // 处理结果
                //打印数据信息
                System.out.println("mc: " + mc + ", ggxh: " + ggxh + ",hh:" + hh + ",dw:" + dw + ",kcs" + kcs + ",pjj:" + pjj + ",kczj:" + kczj);
            }
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } finally {
            // 最后关闭连接资源
            JDBCUtils.close(con, null, null);

            // 关闭statement资源
            JDBCUtils.close(null, pstmt1, null);
            // 关闭reultset资源
            JDBCUtils.close(null, null, resultSet);
        }
    }

    // 计算clggb表总记录数
    public int selectCount() {
        // 获取链接
        Connection con = JDBCUtils.getConnection();
        PreparedStatement pstmt1 = null;
        ResultSet resultSet = null;
        int count = 0;
        String selectSQL = "select count(*) as total from `clggb`"; //查询本仓记录总数
        try {
            pstmt1 = con.prepareStatement(selectSQL);
            // 执行查询语句并获取结果
            resultSet = pstmt1.executeQuery();

            while (resultSet.next()) {
                // 处理结果
                //打印数据信息
                count = resultSet.getInt("total");
                System.out.println("clggb的记录总数为：" + count);
            }
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } finally {
            // 最后关闭连接资源
            JDBCUtils.close(con, pstmt1, resultSet);
        }
        return count;
    }

    // 查询指定行数数据记录
    public String[][] selectClggb(int num1, int num2) {
        // 获取链接
        Connection con = JDBCUtils.getConnection();
        PreparedStatement pstmt1 = null;
        ResultSet resultSet = null;
        String selectSQL = "select `mc`, `ggxh`, `hh`, `dw`, `kcs`, `pjj`, `kczj` from `clggb` limit ?,?";  //查询本仓数据                        ', '                              ', 'B                 ', '      ', 0.00, 0.00, 0.00);
        String[][] arr = new String[num2][7];
        try {
            pstmt1 = con.prepareStatement(selectSQL);
            pstmt1.setInt(1, num1);  //为了对应设置SQL语句中的参数值
            pstmt1.setInt(2, num2);
            // 执行查询语句并获取结果
            resultSet = pstmt1.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                // 获取每个结果的列值
                arr[i][0] = resultSet.getString("mc");
                arr[i][1] = resultSet.getString("ggxh");
                arr[i][2] = resultSet.getString("hh");
                arr[i][3] = resultSet.getString("dw");
                arr[i][4] = Integer.toString(resultSet.getInt("kcs"));
                arr[i][5] = Integer.toString(resultSet.getInt("pjj"));
                arr[i][6] = Integer.toString(resultSet.getInt("kczj"));
                i++;
                // 处理结果
                //打印数据信息
            }

        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } finally {
            // 最后关闭连接资源
            JDBCUtils.close(con, pstmt1, resultSet);
        }
        return arr;
    }

    public void updateClggb(String id,String newName) throws SQLException {
        Connection con = JDBCUtils.getConnection();
        String updatesql = "UPDATE `clggb` SET `mc`=? WHERE `hh`=?";
        PreparedStatement pstmt2 = con.prepareStatement(updatesql);

        try {
            pstmt2.setString(1,newName);
            pstmt2.setString(2,id);
            pstmt2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 最后关闭连接资源
            JDBCUtils.close(con, pstmt2, null);
        }
    }

    public void insertClggb(String name) throws SQLException {
        String[] split = name.split(":");
        String newid = split[0];
        String newname = split[1];

        if (selectid(newid)){
            System.out.println("该货号已存在！");
        }
        Connection con = JDBCUtils.getConnection();
        ResultSet resultSet = null;
        String insertSQL = "INSERT INTO clggb(`hh`,`mc`) VALUES(?,?)";
        PreparedStatement pstmt = con.prepareStatement(insertSQL);
        try {
            pstmt.setString(1,newid);
            pstmt.setString(2,newname);
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(con,pstmt,resultSet);
        }
    }

    public void rkInsert(String []arr) throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        connection = JDBCUtils.getConnection();
        String sql = "insert into clggb(mc,ggxh,hh,dw) values(?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        try {
            pstmt.setString(1,arr[0]);
            pstmt.setString(2,arr[1]);
            pstmt.setString(3,arr[2]);
            pstmt.setString(4,arr[3]);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(connection,pstmt,resultSet);
        }
    }

    public int deleteClggb(String id){
        Connection con = JDBCUtils.getConnection();
        String deleteSQL = "DELETE  FROM clggb WHERE `hh`=?";
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(deleteSQL);
            pstmt.setString(1,id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(con,pstmt,null);
        }
    }

    public static void main(String[] args) throws SQLException {

        ClggbDao test = new ClggbDao();
//		test.select("B01010101         ");    //查询id为B01010101         的数据

//      test.selectClggball();
//        test.selectCount();

//        String[][] arr = test.selectClggb(5, 5);
//        System.out.println(arr.length);
//        for (int j = 0; j < arr.length; j++) {
//            System.out.println("mc:" + arr[j][0] + "ggxh:" + arr[j][1] + "hh:" + arr[j][2] + "dw:" + arr[j][3] + "kcs:" + arr[j][4] + "pjj:" + arr[j][5] + "kczj:" + arr[j][6]);
//        }

//        try {
//            test.updateClggb("B04","螺丝");
//            System.out.println("修改成功！");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        test.insertClggb("BB06:BB01");

//        if(test.selectid("BB06")){
//            System.out.println("存在！");
//        }else System.out.println("不存在！");
        String []arr = new String[]{"螺丝","zz","BB4","只"};
        test.rkInsert(arr);
        System.out.println("完成!");
    }
}
