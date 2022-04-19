package com.yago.epidemic_management.controller;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.common.ResultResponse;
import com.yago.epidemic_management.model.dto.add.AddUserDto;
import com.yago.epidemic_management.model.dto.update.AdminUpdateUserDto;
import com.yago.epidemic_management.model.pojo.User;
import com.yago.epidemic_management.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Yago
 * @Date: 2022/3/22 21:12
 * Description:
 **/
@CrossOrigin
@RestController
public class AdminController {

    @Autowired
    UserService userService;

    /**
     * 管理员用户列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("管理员列表")
    @CrossOrigin
    @GetMapping("/admin/adminList")
    public ResultResponse adminListForAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize, String username) {

        if (username.isEmpty()) {
            //如果username为空，则查询管理员列表
            PageInfo pageInfo = userService.adminListForAdmin(pageNum, pageSize);
            return ResultResponse.success(pageInfo);
        } else {
            //如果username不为空，则查询管理员列表
            User user = userService.selectByName(username);
            return ResultResponse.success(user);
        }
    }

    /**
     * 后台用户列表
     *
     * @param pageNum
     * @param pageSize
     * @return http://127.0.0.1:8081/admin/user/userList?username=&pageNum=1&pageSize=5
     */
    @ApiOperation("用户列表")
    @CrossOrigin
    @GetMapping("/admin/user/userList")
    public ResultResponse userListForAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize, String username) {
        if (username.isEmpty()) {
            PageInfo pageInfo = userService.userListForAdmin(pageNum, pageSize);
            return ResultResponse.success(pageInfo);
        } else {
            User user = userService.selectByName(username);
            return ResultResponse.success(user);
        }
    }

    /**
     * 单个用户详情
     *
     * @param userId
     * @return
     */
    @ApiOperation("单个用户详情")
    @GetMapping("/user/userDetail")
    public ResultResponse userDetail(@RequestParam String userId) {
        User user = userService.selectByPrimaryKey(userId);
        return ResultResponse.success(user);
    }

    /**
     * 后台更新用户
     *
     * @param userDto
     * @return
     */
    @ApiOperation("管理员更新用户")
    @PostMapping("admin/user/update")
    public ResultResponse updateUser(@Validated @RequestBody AdminUpdateUserDto userDto) {
        User user = new User();

        BeanUtils.copyProperties(userDto, user);
        userService.update(user);
        return ResultResponse.success();
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return http://127.0.0.1:8081/user/delete?userId=1   【传统风格】
     * http://127.0.0.1:8081/user/delete/1    【Restful风格】
     */
    @PostMapping("/admin/user/delete/{userId}")
    public ResultResponse deleteUser(@PathVariable String userId) {
        System.out.println(userId);
        userService.deleteUser(userId);
        return ResultResponse.success();
    }

    /**
     * 添加用户
     *
     * @param addUserDto
     * @return
     */
    @PostMapping("/user/add")
    public ResultResponse addUser(@RequestBody AddUserDto addUserDto) {
        userService.addUser(addUserDto);
        return ResultResponse.success();
    }

    /**
     * 批量设置用户禁用
     *
     * @param ids
     * @param status
     * @return
     */
    @ApiOperation("批量设置用户禁用")
    @PostMapping("/admin/batchDeleteEgressUser")
    public ResultResponse batchDeleteEgressUser(@RequestBody String[] ids, @RequestParam Integer status) {
        userService.batchDeleteEgressUser(ids, status);
        return ResultResponse.success();
    }
}
