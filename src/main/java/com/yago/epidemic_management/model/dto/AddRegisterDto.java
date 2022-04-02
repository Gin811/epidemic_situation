package com.yago.epidemic_management.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class AddRegisterDto {

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "号码不能为空")
    private String phone;

    @NotBlank(message = "所在地不能为空")
    private String szd;

    private String stzk;

    @NotBlank(message = "是否和感染人员接触不能为空")
    private String grjc;

    private String bz;

    @NotNull(message = "时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;

    @NotBlank(message = "体温不能为空")
    private String tw;

    @NotBlank(message = "最近14天信息不能为空")
    private String fourteenDay;

    public String getFourteenDay() {
        return fourteenDay;
    }

    public void setFourteenDay(String fourteenDay) {
        this.fourteenDay = fourteenDay;
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

    public Date getCreateTime() {
        return create_time;
    }

    public void setCreateTime(Date create_time) {
        this.create_time = create_time;
    }

    public String getTw() {
        return tw;
    }

    public void setTw(String tw) {
        this.tw = tw == null ? null : tw.trim();
    }
}
