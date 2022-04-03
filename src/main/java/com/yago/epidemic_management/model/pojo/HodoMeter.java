package com.yago.epidemic_management.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class HodoMeter {
    private Integer id;

    private String username;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cx_time;

    private String place;

    private String bz;

    private String mobile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getCx_time() {
        return cx_time;
    }

    public void setCx_time(Date cx_time) {
        this.cx_time = cx_time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }
}
