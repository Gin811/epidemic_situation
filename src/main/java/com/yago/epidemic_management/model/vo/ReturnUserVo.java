package com.yago.epidemic_management.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author: YaGo
 * @Date: 2022/4/12 10:51
 * Description:
 **/
public class ReturnUserVo {
    private Integer id;

    private Integer userId;

    private String username;

    private String mobile;

    private String content;

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String bz;

    private String stzk;

    private String fourteenDay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getStzk() {
        return stzk;
    }

    public void setStzk(String stzk) {
        this.stzk = stzk;
    }

    public String getFourteenDay() {
        return fourteenDay;
    }

    public void setFourteenDay(String fourteenDay) {
        this.fourteenDay = fourteenDay;
    }
}
