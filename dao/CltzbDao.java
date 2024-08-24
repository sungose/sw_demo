package com.echo.dao;

import com.echo.javabean.Clgg;
import com.echo.javabean.Clll;
import com.echo.javabean.Clrk;
import com.echo.utils.JDBCUtils;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.Vector;

public class CltzbDao {
    public int clrkCount(){
        Connection con = JDBCUtils.getConnection();
        PreparedStatement pstmt1 = null;
        ResultSet resultSet = null;
        int count = 0;
        String selectSQL = "select count(*) as total from `clrk`"; //查询clrk表记录总数
        try {
            pstmt1 = con.prepareStatement(selectSQL);
            // 执行查询语句并获取结果
            resultSet = pstmt1.executeQuery();

            while (resultSet.next()) {
                // 处理结果
                //打印数据信息
                count = resultSet.getInt("total");
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

    public int clrkInsert(Clrk clrk) throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        CltzbDao cltzbDao = new CltzbDao();
        int id = 0;
        int i = 0;
        id = cltzbDao.clrkCount() + 1;
        String ckbh = clrk.getCkbh();
        String hh = clrk.getHh();
        Integer sl = clrk.getSl();
        Double dj =  clrk.getDj();
        Double je =  clrk.getJe();
        String ywlb = clrk.getYwlb();
        connection = JDBCUtils.getConnection();
        String sql = "insert into clrk(id,ckbh,hh,sl,dj,je,ywlb) values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        try {
            pstmt.setInt(1,id);
            pstmt.setString(2,ckbh);
            pstmt.setString(3,hh);
            pstmt.setInt(4,sl);
            pstmt.setDouble(5, dj);
            pstmt.setDouble(6,je);
            pstmt.setString(7,ywlb);
            i = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(connection,pstmt,resultSet);
        }
        return i;
    }

    public Clrk select(String id) {
        // 获取链接
        Connection con = JDBCUtils.getConnection();
        PreparedStatement pstmt1 = null;
        ResultSet resultSet = null;
        Clrk clrk = null;
        String selectSQL = "select `id`, `ckbh`, `hh`, `sl`, `dj`, `je`, `ywlb` from `clrk`";  //查询本仓数据                        ', '                              ', 'B                 ', '      ', 0.00, 0.00, 0.00);
        try {
            pstmt1 = con.prepareStatement(selectSQL);
            // 执行查询语句并获取结果
            resultSet = pstmt1.executeQuery();
            while (resultSet.next()) {
                // 获取每个结果的列值
                clrk = new Clrk();
                clrk.setId(resultSet.getInt("id"));
                clrk.setCkbh(resultSet.getString("ckbh"));
                clrk.setHh(resultSet.getString("hh"));
                clrk.setSl(resultSet.getInt("sl"));
                clrk.setDj(resultSet.getDouble("dj"));
                clrk.setJe(resultSet.getDouble("je"));
                clrk.setYwlb(resultSet.getString("ywlb"));

            }
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } finally {
            // 最后关闭连接资源
            JDBCUtils.close(con, pstmt1, resultSet);
        }

        return clrk;
    }

    public List<Vector> selectAll() {
        // 获取链接
        Connection con = JDBCUtils.getConnection();
        PreparedStatement pstmt1 = null;
        ResultSet resultSet = null;
        Clgg clgg = null;
        String selectSQL = "select `mc`, `ggxh`, `hh`, `dw`, `kcs`, `pjj`, `kczj` from `clggb` order by hh";  //查询本仓数据                        ', '                              ', 'B                 ', '      ', 0.00, 0.00, 0.00);
        try {
            pstmt1 = con.prepareStatement(selectSQL);
            // 执行查询语句并获取结果
            resultSet = pstmt1.executeQuery();
            while (resultSet.next()) {
                // 获取每个结果的列值
                clgg = new Clgg();
                clgg.setMc(resultSet.getString("mc"));
                clgg.setGgxh(resultSet.getString("ggxh"));
                clgg.setHh(resultSet.getString("hh"));
                clgg.setDw(resultSet.getString("dw"));
                clgg.setKcs(resultSet.getDouble("kcs"));
                clgg.setPjj(resultSet.getDouble("pjj"));
                clgg.setKczj(resultSet.getDouble("kczj"));
            }
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } finally {
            // 最后关闭连接资源
            JDBCUtils.close(con, pstmt1, resultSet);
        }

        return (List<Vector>) clgg;
    }

    public int clllInsert(Clll clll) throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        int i = 0;
        String hh = clll.getHh();
        String ckbh = clll.getCkbh();
        int sl =  clll.getSl();
        Date lysj =  clll.getLysj();
        String recipient = clll.getRecipient();
        String status = clll.getStstus();
        connection = JDBCUtils.getConnection();
        String sql = "insert into clll(hh,ckbh,sl,lysj,recipient,status) values(?,?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        try {
//            pstmt.setInt(1,ll_id);
            pstmt.setString(1,hh);
            pstmt.setString(2,ckbh);
            pstmt.setInt(3,sl);
            pstmt.setDate(4,  lysj);
            pstmt.setString(5,recipient);
            pstmt.setString(6,status);
            i = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(connection,pstmt,resultSet);
        }
        return i;
    }

    public static void main(String args[]) throws SQLException {
        CltzbDao cltzbDao = new CltzbDao();
        Clll clll = new Clll();
        int count = 0;
//        clll.setLl_id(null);
        clll.setHh("B01010305");
        clll.setCkbh("E");
        clll.setSl(2);
        clll.setLysj(Date.valueOf("2024-5-30"));
        clll.setRecipient("王晓明");
        clll.setStstus("待领用");
        count = cltzbDao.clllInsert(clll);
        System.out.println(count);
        System.out.println("完成");
    }
}
