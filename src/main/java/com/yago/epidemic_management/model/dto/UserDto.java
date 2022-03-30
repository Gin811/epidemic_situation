package com.yago.epidemic_management.model.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: Yago
 * @Date: 2022/3/26 21:03
 * Description:
 **/
public class UserDto implements Serializable {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
