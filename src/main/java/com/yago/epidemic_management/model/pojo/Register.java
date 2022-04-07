package com.yago.epidemic_management.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Register {
    private Integer id;

    private String name;

    private String phone;

    private String szd;

    private String stzk;

    private String grjc;

    private String bz;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String tw;

    private String fourteenDay;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFourteenDay() {
        return fourteenDay;
    }

    public void setFourteenDay(String fourteenDay) {
        this.fourteenDay = fourteenDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getSzd() {
        return szd;
    }

    public void setSzd(String szd) {
        this.szd = szd == null ? null : szd.trim();
    }

    public String getStzk() {
        return stzk;
    }

    public void setStzk(String stzk) {
        this.stzk = stzk == null ? null : stzk.trim();
    }

    public String getGrjc() {
        return grjc;
    }

    public void setGrjc(String grjc) {
        this.grjc = grjc == null ? null : grjc.trim();
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    public String getTw() {
        return tw;
    }

    public void setTw(String tw) {
        this.tw = tw == null ? null : tw.trim();
    }
}
