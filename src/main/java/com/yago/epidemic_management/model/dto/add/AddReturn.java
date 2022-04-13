package com.yago.epidemic_management.model.dto.add;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class AddReturn {
    private Integer id;

    @NotBlank(message = "申请信息不能为空")
    private String content;

    @NotNull(message = "返回时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String bz;


    @NotNull(message = "userId不能为空")
    private Integer userId;

    private String stzk;

    @NotBlank(message = "近14天不能为空")
    private String fourteenDay;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
