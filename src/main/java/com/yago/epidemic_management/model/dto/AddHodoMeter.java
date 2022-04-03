package com.yago.epidemic_management.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class AddHodoMeter {

    @NotBlank(message = "姓名不能为空")
    private String username;

    @NotNull(message = "出行时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cxTime;

    @NotBlank(message = "时间不能为空")
    private String place;

    private String bz;

    @NotBlank(message = "联系方式不能为空")
    private String mobile;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getCxTime() {
        return cxTime;
    }

    public void setCxTime(Date cxTime) {
        this.cxTime = cxTime;
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
