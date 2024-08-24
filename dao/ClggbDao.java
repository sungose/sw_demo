package com.echo.dao;

import com.echo.javabean.Clgg;
import com.echo.utils.JDBCUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

/**
 * ClggbDao
 */
public class ClggbDao {
    public int getCount() {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        ResultSet resultSet = null;
        int count = 0;

        try {
            // 获取链接
            conn = JDBCUtils.getConnection();

            String sql = "select count(`mc`) from clggb";
            pstmt1 = conn.prepareStatement(sql);
            resultSet = pstmt1.executeQuery(sql);

            while (resultSet.next()) {
                String val = resultSet.getString(1);
                count = Integer.parseInt(val);   // ?
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, pstmt1, resultSet);
            return count;
        }
    }


    // todo 分页方法
    public List<Vector> getPageList(String mc, int page, int pageSize) {
        if (page < 0) {
            page = 1;
        }
        if (pageSize < 0) {
            pageSize = 10;
        }
        int start = (page - 1) * pageSize;   // 开始查询的记录数的计算


        Connection conn = null;
        PreparedStatement pstmt1 = null;
        ResultSet resultSet = null;

        List<Vector> list = new ArrayList<>();
        try {
            // 获取链接
            conn = JDBCUtils.getConnection();
            String sql = "select `mc`, `ggxh`, `hh`, `dw`, `kcs`, `pjj`, `kczj` from clggb ";

            if (!Objects.isNull(mc)) {
                sql += "where `mc` like ? order by `mc` desc limit ?,?";
                // todo like在SQL中的意义
                System.out.println("sql ==>" + sql);

                pstmt1 = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                // todo 思考不同
//                 pstmt1.setObject(1, mc);     // 通过全名称搜索
                pstmt1.setObject(1, "%" + mc + "%");    // 可通过关键字搜索


                pstmt1.setObject(2, start);
                pstmt1.setObject(3, pageSize);
            } else {
                sql += "order by `mc` desc limit ?,?";
                System.out.println("sql ==>" + sql);

                pstmt1 = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstmt1.setObject(1, start);
                pstmt1.setObject(2, pageSize);
            }


            resultSet = pstmt1.executeQuery();
            while (resultSet.next()) {
                Vector rowV = new Vector();
                rowV.add(resultSet.getString(1));
                rowV.add(resultSet.getString(2));
                rowV.add(resultSet.getString(3));
                rowV.add(resultSet.getString(4));
                rowV.add(resultSet.getString(5));
                rowV.add(resultSet.getString(6));
                rowV.add(resultSet.getString(7));

                list.add(rowV);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, pstmt1, resultSet);
            return list;
        }
    }

    public int clggbInsert(Clgg clgg) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        int i = 0;
        String mc = clgg.getMc();
        String ggxh = clgg.getGgxh();
        String hh = clgg.getHh();
        String dw = clgg.getDw();
        Double kcs = clgg.getKcs();
        Double pjj = clgg.getPjj();
        Double kczj = clgg.getKczj();
        conn = JDBCUtils.getConnection();
        String sql = "insert into clggb(mc,ggxh,hh,dw,kcs,pjj,kczj) values(?,?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        try {
            pstmt.setString(1, mc);
            pstmt.setString(2, ggxh);
            pstmt.setString(3, hh);
            pstmt.setString(4, dw);
            pstmt.setDouble(5, kcs);
            pstmt.setDouble(6, pjj);
            pstmt.setDouble(7, kczj);
            i = pstmt.executeUpdate();
            // todo 可以再完善 判断是否新增成功
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, pstmt, res);
        }
        return i;
    }

    public int clggbdelet(Clgg clgg) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        int i = 0;
        String mc = clgg.getMc();
        String ggxh = clgg.getGgxh();
        String hh = clgg.getHh();

        conn = JDBCUtils.getConnection();
        String sql = "delete from clggb where mc=? and ggxh=? and hh=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, mc);
        pstmt.setString(2, ggxh);
        pstmt.setString(3, hh);
        try {
            i = pstmt.executeUpdate();

            // todo 可以再完善 判断是否新增成功
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, pstmt, res);
        }
        return i;
    }

    public Clgg select(String id) {
        // 获取链接
        Connection con = JDBCUtils.getConnection();
        PreparedStatement pstmt1 = null;
        ResultSet resultSet = null;
        Clgg clgg = null;
        String selectSQL = "select `mc`, `ggxh`, `hh`, `dw`, `kcs`, `pjj`, `kczj` from `clggb` where hh=?";  //查询本仓数据                        ', '                              ', 'B                 ', '      ', 0.00, 0.00, 0.00);
        try {
            pstmt1 = con.prepareStatement(selectSQL);
            pstmt1.setString(1, id);
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

        return clgg;
    }

    public Clgg selectAll() {
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

        return clgg;
    }

}