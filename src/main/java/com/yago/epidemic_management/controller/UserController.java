package com.yago.epidemic_management.controller;

import com.yago.epidemic_management.common.ResultResponse;
import com.yago.epidemic_management.exception.ExceptionEnum;
import com.yago.epidemic_management.model.dto.update.UpdateUserDto;
import com.yago.epidemic_management.model.dto.update.UserDto;
import com.yago.epidemic_management.model.pojo.User;
import com.yago.epidemic_management.service.UserService;
import com.yago.epidemic_management.utils.JWTUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
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
     * @param mobile
     * @param password
     * @return
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    @ResponseBody
    public ResultResponse register(@RequestParam("mobile") String mobile,
                                   @RequestParam("password") String password) {
        //1.校验：判断用户名是否为空
        if (StringUtils.isEmpty(mobile)) {
            return ResultResponse.error(ExceptionEnum.NEED_MOBILE);
        }
        //2.校验：判断密码是否为空
        if (StringUtils.isEmpty(password)) {
            return ResultResponse.error(ExceptionEnum.NEED_PASSWORD);
        }
        if (password.length() < 8) {
            return ResultResponse.error(ExceptionEnum.PASSWORD_TOO_SHORT);
        }
        userService.register(mobile, password);
        return ResultResponse.success();
    }

    /**
     * 管理员登录
     *
     * @param userDto
     * @param request
     * @param response
     * @return
     */
    @ApiOperation("管理员登录")
    @PostMapping("/adminLogin")
    @ResponseBody
    public ResultResponse adminLogin(@RequestBody UserDto userDto, HttpServletRequest request, HttpServletResponse response) {
        String mobile = userDto.getMobile();
        String password = userDto.getPassword();
        //1.校验：判断用户名是否为空
        if (StringUtils.isEmpty(mobile)) {
            return ResultResponse.error(ExceptionEnum.NEED_USER_NAME);
        }
        //2.校验：判断密码是否为空
        if (StringUtils.isEmpty(password)) {
            return ResultResponse.error(ExceptionEnum.NEED_PASSWORD);
        }
        User user = userService.login(mobile, password);
        //3.判断是否是管理员;是管理员，可以执行操作;
        if (userService.checkAdminRole(user)) {
            //保存用户信息时，不保存密码
            user.setPassword(null);

            /*  返回给浏览器的响应头
             *  response.setHeader("Authorization", Constant.MPIDEMICMANAGEMENT_USER);
             *  response.setHeader("Access-control-Expose-Headers", "Authorization");
             */
            response.setHeader(JWTUtils.USER_LOGIN_TOKEN, user.getToken());
            response.setHeader("Access-control-Expose-Headers", "USER_LOGIN_TOKEN");

//            //将用户信息存在session中
//            request.getSession().setAttribute(Constant.MPIDEMICMANAGEMENT_USER, user.getUsername());
//            Object attribute = request.getSession().getAttribute(Constant.MPIDEMICMANAGEMENT_USER);
//            System.out.println("登录时的session为："+attribute.toString());

            return ResultResponse.success(user);
        } else {
            return ResultResponse.error(ExceptionEnum.NEED_ADMIN);
        }
    }

    /**
     * 普通用户注册
     *
     * @param user1
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    @ResponseBody
    public ResultResponse login(@RequestBody Map<String, String> user1) {
        String mobile = user1.get("mobile");
        String password = user1.get("password");
        //1.校验：判断用户名是否为空
        if (StringUtils.isEmpty(mobile)) {
            return ResultResponse.error(ExceptionEnum.NEED_MOBILE);
        }
        //2.校验：判断密码是否为空
        if (StringUtils.isEmpty(password)) {
            return ResultResponse.error(ExceptionEnum.NEED_PASSWORD);
        }
        //3.查询用户
        User user = userService.login(mobile, password);
        //保存用户信息时，不保存密码
        user.setPassword(null);
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
    public ResultResponse logout(HttpSession session) {
//        if (session.getAttribute(Constant.MPIDEMICMANAGEMENT_USER) == null) {
//            return ResultResponse.error(ExceptionEnum.NO_USER);
//        }
//        System.out.println("session为：" + session.getAttribute(Constant.MPIDEMICMANAGEMENT_USER));
//        session.removeAttribute(Constant.MPIDEMICMANAGEMENT_USER);
//        System.out.println("session为：" + session.getAttribute(Constant.MPIDEMICMANAGEMENT_USER));
        return ResultResponse.success();
    }

    /**
     * 用户更新信息
     *
     * @param userDto
     * @return
     */
    @ApiOperation("用户更新信息")
    @PostMapping("user/update")
    @ResponseBody
    public ResultResponse updateUser(@Validated @RequestBody UpdateUserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        userService.update(user);
        return ResultResponse.success();
    }
}
