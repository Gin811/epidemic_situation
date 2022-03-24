package com.yago.epidemic_management.controller;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.common.ResultResponse;
import com.yago.epidemic_management.model.dto.UpdateUserDto;
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
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    /**
     * 后台用户列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("用户列表")
    @GetMapping("/user/userlist")
    public ResultResponse userListForAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {

        PageInfo pageInfo = userService.userListForAdmin(pageNum, pageSize);
        return ResultResponse.success(pageInfo);
    }

    /**
     * 单个用户详情
     *
     * @param userId
     * @return
     */
    @ApiOperation("单个用户详情")
    @GetMapping("/user/userDetail")
    public ResultResponse userDetail(@RequestParam("userId") Integer userId) {
        User user = userService.selectByPrimaryKey(userId);
        return ResultResponse.success(user);
    }

    /**
     * 后台更新列表
     *
     * @param userDto
     * @return
     */
    @ApiOperation("管理员更新用户")
    @PostMapping("/user/update")
    public ResultResponse updateUser(@Validated @RequestBody UpdateUserDto userDto) {
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
    @PostMapping("/user/delete")
    public ResultResponse deleteUser(@RequestParam Integer id, @RequestParam Integer newStatus) {
        userService.changUserStatus(id, newStatus);
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
    @PostMapping("/batchDeleteEgressUser")
    public ResultResponse batchDeleteEgressUser(@RequestParam Integer[] ids, @RequestParam Integer status) {
        userService.batchDeleteEgressUser(ids, status);
        return ResultResponse.success();
    }
}