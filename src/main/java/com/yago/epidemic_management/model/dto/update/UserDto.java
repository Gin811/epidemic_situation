package com.yago.epidemic_management.model.dto.update;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: Yago
 * @Date: 2022/3/26 21:03
 * Description:
 **/
public class UserDto implements Serializable {
    @NotBlank(message = "号码不能为空")
    private String mobile;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String token;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
