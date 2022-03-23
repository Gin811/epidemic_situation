package com.yago.epidemic_management.model.pojo;

import java.util.Date;

public class Leave {
    private Integer id;

    private Date wcTime;

    private String wcYy;

    private String wcDd;

    private String username;

    private String phone;

    private String bz;

    private String stu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getWcTime() {
        return wcTime;
    }

    public void setWcTime(Date wcTime) {
        this.wcTime = wcTime;
    }

    public String getWcYy() {
        return wcYy;
    }

    public void setWcYy(String wcYy) {
        this.wcYy = wcYy == null ? null : wcYy.trim();
    }

    public String getWcDd() {
        return wcDd;
    }

    public void setWcDd(String wcDd) {
        this.wcDd = wcDd == null ? null : wcDd.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    public String getStu() {
        return stu;
    }

    public void setStu(String stu) {
        this.stu = stu == null ? null : stu.trim();
    }
}