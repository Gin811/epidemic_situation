package com.yago.epidemic_management.controller;

import com.yago.epidemic_management.common.Constant;
import com.yago.epidemic_management.common.ResultResponse;
import com.yago.epidemic_management.exception.ExceptionEnum;
import com.yago.epidemic_management.model.pojo.User;
import com.yago.epidemic_management.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Author: Yago
 * @Date: 2022/3/22 10:30
 * Description:
 **/
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation("测试接口")
    @GetMapping("/test")
    @ResponseBody
    public User getUser(Integer id) {
        return userService.selectByPrimaryKey(id);
    }

    /**
     * 普通用户注册
     *
     * @param username
     * @param password
     * @return
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    @ResponseBody
    public ResultResponse register(@RequestParam("username") String username,
                                   @RequestParam("password") String password) {
        //1.校验：判断用户名是否为空
        if (StringUtils.isEmpty(username)) {
            return ResultResponse.error(ExceptionEnum.NEED_USER_NAME);
        }
        //2.校验：判断密码是否为空
        if (StringUtils.isEmpty(password)) {
            return ResultResponse.error(ExceptionEnum.NEED_PASSWORD);
        }
        if (password.length() < 8) {
            return ResultResponse.error(ExceptionEnum.PASSWORD_TOO_SHORT);
        }
        userService.register(username, password);
        return ResultResponse.success();
    }

    /**
     * 普通用户注册
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    @ResponseBody
    public ResultResponse login(@RequestParam("username") String username,
                                @RequestParam("password") String password, HttpSession session) {
        //1.校验：判断用户名是否为空
        if (StringUtils.isEmpty(username)) {
            return ResultResponse.error(ExceptionEnum.NEED_USER_NAME);
        }
        //2.校验：判断密码是否为空
        if (StringUtils.isEmpty(password)) {
            return ResultResponse.error(ExceptionEnum.NEED_PASSWORD);
        }
        //3.查询用户
        User user = userService.login(username, password);
        //保存用户信息时，不保存密码
        user.setPassword(null);
        //将用户信息存在session中
        session.setAttribute(Constant.MPIDEMICMANAGEMENT_USER, user);
        return ResultResponse.success(user);
    }


    /**
     * 退出并清楚
     *
     * @param session
     * @return
     */
    @ApiOperation("用户注销")
    @PostMapping("/logout")
    @ResponseBody
    public ResultResponse logout(HttpSession session) {
        session.removeAttribute(Constant.MPIDEMICMANAGEMENT_USER);
        return ResultResponse.success();
    }

}
