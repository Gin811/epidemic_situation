package com.yago.epidemic_management.model.dto.update;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Yago
 * @Date: 2022/3/22 14:29
 * Description:Dto是用于接受前端数据得类，用于服务层
 **/
@Data
public class AdminUpdateUserDto implements Serializable {
    @NotNull(message = "userId不能为空")
    private Integer userId;

    @NotBlank(message = "username不能为null")
    private String username;

    private String password;

    @Email(message = "邮箱格式不对")
    private String email;

    @Size(min = 11, max = 11)
    private String mobile;

    private Integer status;

    @NotNull(message = "创建者id不能为空")
    private Long createUserId;

    @NotNull(message = "更新时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
