package com.echo.javabean;

import java.sql.Date;

// 材料领用类
public class Clll {
    private Integer ll_id;
    private String hh;
    private String ckbh;
    private Integer sl;
    private Date lysj;
    private String recipient;
    private String ststus;
    private String day;
    public Integer getLl_id() {
        return ll_id;
    }

    public void setLl_id(Integer ll_id) {
        this.ll_id = ll_id;
    }

    public String getHh() {
        return hh;
    }

    public void setHh(String hh) {
        this.hh = hh;
    }

    public String getCkbh() {
        return ckbh;
    }

    public void setCkbh(String ckbh) {
        this.ckbh = ckbh;
    }

    public Integer getSl() {
        return sl;
    }

    public void setSl(Integer sl) {
        this.sl = sl;
    }

    public Date getLysj() {
        return lysj;
    }

    public void setLysj(Date lysj) {
        this.lysj = lysj;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getStstus() {
        return ststus;
    }

    public void setStstus(String ststus) {
        this.ststus = ststus;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

}
