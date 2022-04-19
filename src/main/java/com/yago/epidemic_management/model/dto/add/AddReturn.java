package com.yago.epidemic_management.model.dto.add;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class AddReturn {
    @NotBlank(message = "姓名不能为空")
    private String userName;
    @NotBlank(message = "号码不能为空")
    private String mobile;

    @NotBlank(message = "申请信息不能为空")
    private String content;

    @NotNull(message = "返回时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String bz;

    private String stzk;

    @NotBlank(message = "近14天不能为空")
    private String fourteenDay;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        this.content = content == null ? null : content.trim();
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
        this.bz = bz == null ? null : bz.trim();
    }

    public String getStzk() {
        return stzk;
    }

    public void setStzk(String stzk) {
        this.stzk = stzk == null ? null : stzk.trim();
    }

    public String getFourteenDay() {
        return fourteenDay;
    }

    public void setFourteenDay(String fourteenDay) {
        this.fourteenDay = fourteenDay == null ? null : fourteenDay.trim();
    }
}
