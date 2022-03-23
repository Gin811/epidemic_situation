package com.yago.epidemic_management.controller;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.common.Constant;
import com.yago.epidemic_management.common.ResultResponse;
import com.yago.epidemic_management.exception.ExceptionEnum;
import com.yago.epidemic_management.model.dto.UserDto;
import com.yago.epidemic_management.model.pojo.User;
import com.yago.epidemic_management.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Author: Yago
 * @Date: 2022/3/22 21:12
 * Description:
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    /**
     * 管理员登录
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @ApiOperation("管理员登录")
    @PostMapping("/login")
    public ResultResponse adminLogin(@RequestParam("username") String username,
                                     @RequestParam("password") String password, HttpSession session) {
        //1.校验：判断用户名是否为空
        if (StringUtils.isEmpty(username)) {
            return ResultResponse.error(ExceptionEnum.NEED_USER_NAME);
        }
        //2.校验：判断密码是否为空
        if (StringUtils.isEmpty(password)) {
            return ResultResponse.error(ExceptionEnum.NEED_PASSWORD);
        }
        User user = userService.login(username, password);
        //判断是否是管理员
        if (userService.checkAdminRole(user)) {
            //是管理员，可以执行操作
            //保存用户信息时，不保存密码
            user.setPassword(null);
            //将用户信息存在session中
            session.setAttribute(Constant.MPIDEMICMANAGEMENT_USER, user);
            return ResultResponse.success(user);
        } else {
            return ResultResponse.error(ExceptionEnum.NEED_ADMIN);
        }
    }

    /**
     * 后台用户列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("用户列表")
    @GetMapping("/userlist")
    public ResultResponse userListForAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {

        PageInfo pageInfo = userService.userListForAdmin(pageNum, pageSize);
        return ResultResponse.success(pageInfo);
    }

    /**
     * 后台更新列表
     *
     * @param userDto
     * @return
     */
    @ApiOperation("管理员编辑用户")
    @PostMapping("/update")
    public ResultResponse updateUser(@Validated @RequestBody UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        userService.update(user);
        return ResultResponse.success();
    }

    /**
     * 软删除用户
     * 为了保证信息库的沉淀，一般不推荐删除，可以更新或者禁用，变相软删除
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public ResultResponse deleteUser(@RequestParam Integer id, @RequestParam Integer newId) {
        userService.changUserStatus(id, newId);
        return ResultResponse.success();
    }
}