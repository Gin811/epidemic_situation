package com.yago.epidemic_management.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class UpdateLeaveDto {
    @NotNull(message = "id不能为空")
    private Integer id;

    @NotNull(message = "外出时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date wcTime;

    @NotBlank(message = "外出原因不能为空")
    private String wcYy;

    @NotBlank(message = "外出地点不能为空")
    private String wcDd;

    @NotBlank(message = "姓名不能为空")
    private String username;

    @NotBlank(message = "联系方式不能为空")
    private String phone;

    private String bz;

    @NotBlank(message = "健康状态不能为空")
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