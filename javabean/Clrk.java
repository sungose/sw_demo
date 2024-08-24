package com.echo.javabean;

import com.echo.dao.ClggbDao;

public class Clrk {
    private Integer id;
    private String ckbh;
    private String hh;
    private Integer sl;
    private Double dj;
    private Double je;

    // 如果数据库中的字段名称叫yw_lb,则在javabean中命名为ywLb
    private String ywlb;
    private String ywzt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCkbh() {
        return ckbh;
    }

    public void setCkbh(String ckbh) {
        this.ckbh = ckbh;
    }

    public String getHh() {
        return hh;
    }

    public void setHh(String hh) {
        this.hh = hh;
    }

    public Integer getSl() {
        return sl;
    }

    public void setSl(Integer sl) {
        this.sl = sl;
    }

    public Double getDj() {
        return dj;
    }

    public void setDj(Double dj) {
        this.dj = dj;
    }

    public Double getJe() {
        return je;
    }

    public void setJe(Double je) {
        this.je = je;
    }

    public String getYwlb() {
        return ywlb;
    }

    public void setYwlb(String ywlb) {
        this.ywlb = ywlb;
    }

    public String getYwzt() {
        return ywzt;
    }

    public void setYwzt(String ywzt) {
        this.ywzt = ywzt;
    }

    @Override
    public String toString() {
        return "Clrk{" +
                "id=" + id +
                ", ckbh='" + ckbh + '\'' +
                ", hh='" + hh + '\'' +
                ", sl=" + sl +
                ", dj=" + dj +
                ", je=" + je +
                ", ywlb='" + ywlb + '\'' +
                ", ywzt='" + ywzt + '\'' +
                '}';
    }

    // psvm
    public static void main(String[] args) {
        Clrk clrk = new Clrk();
        clrk.setHh("BB");
        Class cs = Clrk.class;
        Class css = ClggbDao.class;
        System.out.println(Clrk.class);
        System.out.println(Clrk.class.getCanonicalName());
        System.out.println(clrk.getClass() == Clrk.class);
        System.out.println(clrk.getClass().getCanonicalName());
    }
}
