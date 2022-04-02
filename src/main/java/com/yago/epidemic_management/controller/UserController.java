package com.yago.epidemic_management.controller;

import com.yago.epidemic_management.common.Constant;
import com.yago.epidemic_management.common.ResultResponse;
import com.yago.epidemic_management.exception.ExceptionEnum;
import com.yago.epidemic_management.model.dto.UserDto;
import com.yago.epidemic_management.model.pojo.User;
import com.yago.epidemic_management.service.UserService;
import com.yago.epidemic_management.utils.JWTUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: Yago
 * @Date: 2022/3/22 10:30
 * Description:
 **/
@Controller
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation("测试接口")
    @GetMapping("/test")
    @ResponseBody
    public User getUser(String username) {
        return userService.selectByName(username);
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
     * 管理员登录
     *
     * @param userDto
     * @param session
     * @return
     */
    @ApiOperation("管理员登录")
    @PostMapping("/adminLogin")
    @ResponseBody
    public ResultResponse adminLogin(@Validated @RequestBody UserDto userDto, HttpSession session, HttpServletResponse response) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        //1.校验：判断用户名是否为空
        if (StringUtils.isEmpty(username)) {
            return ResultResponse.error(ExceptionEnum.NEED_USER_NAME);
        }
        //2.校验：判断密码是否为空
        if (StringUtils.isEmpty(password)) {
            return ResultResponse.error(ExceptionEnum.NEED_PASSWORD);
        }
        User user = userService.login(username, password);
        //3.判断是否是管理员
        if (userService.checkAdminRole(user)) {
            //是管理员，可以执行操作
            //保存用户信息时，不保存密码
            user.setPassword(null);
            //将用户信息存在session中
//            response.setHeader("Authorization", Constant.MPIDEMICMANAGEMENT_USER);
//            response.setHeader("Access-control-Expose-Headers", "Authorization");
            response.setHeader(JWTUtils.USER_LOGIN_TOKEN, user.getToken());
            response.setHeader("Access-control-Expose-Headers", "USER_LOGIN_TOKEN");
//            session.setAttribute(Constant.MPIDEMICMANAGEMENT_USER, user);
            return ResultResponse.success(user);
        } else {
            return ResultResponse.error(ExceptionEnum.NEED_ADMIN);
        }
    }

    /**
     * 普通用户注册
     *
     * @param user1
     * @param session
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    @ResponseBody
    public ResultResponse login(@RequestBody Map<String, String> user1, HttpSession session) {
        String username = user1.get("username");
        String password = user1.get("password");
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
     * @param
     * @return
     */
    @ApiOperation("用户注销")
    @PostMapping("/logout")
    @ResponseBody
    public ResultResponse logout(HttpServletRequest request) {
//        if (session.getAttribute(Constant.MPIDEMICMANAGEMENT_USER) == null) {
//            return ResultResponse.error(ExceptionEnum.NO_USER);
//        }

        HttpSession session = request.getSession();

        System.out.println("session为：" + session.getAttribute(Constant.MPIDEMICMANAGEMENT_USER));
        session.removeAttribute(Constant.MPIDEMICMANAGEMENT_USER);
        System.out.println("session为：" + session.getAttribute(Constant.MPIDEMICMANAGEMENT_USER));
        return ResultResponse.success();
    }
}
