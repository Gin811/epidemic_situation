package com.yago.epidemic_management.controller;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.common.ResultResponse;
import com.yago.epidemic_management.model.dto.add.AddLeaveDto;
import com.yago.epidemic_management.model.dto.update.UpdateLeaveDto;
import com.yago.epidemic_management.model.pojo.Leave;
import com.yago.epidemic_management.service.LeaveService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Yago
 * @Date: 2022/3/24 9:35
 * Description: 人员外出控制器
 **/
@CrossOrigin
@RestController
public class LeaveController {
    @Autowired
    LeaveService leaveService;

    /**
     * 查询外出人员列表
     *
     * @param pageNum
     * @param pageSize
     * @param username
     * @return
     */
    @ApiOperation("外出人员列表")
    @GetMapping("/admin/leave/egressList")
    public ResultResponse getLeaveList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, String username) {
        if (username.isEmpty()) {
            PageInfo leaveList = leaveService.getLeaveList(pageNum, pageSize);
            return ResultResponse.success(leaveList);
        } else {
            Leave leave = leaveService.selectByName(username);
            return ResultResponse.success(leave);
        }
    }

    /**
     * 处理外出申请
     *
     * @param pageNum
     * @param pageSize
     * @param username
     * @return
     */
    @ApiOperation("处理人员外出申请")
    @GetMapping("/admin/leave/processingApplications")
    public ResultResponse ProcessingApplications(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, String username) {
        if (username.isEmpty()) {
            PageInfo leaveList = leaveService.getApplications(pageNum, pageSize);
            return ResultResponse.success(leaveList);
        } else {
            Leave leave = leaveService.selectByName(username);
            return ResultResponse.success(leave);
        }
    }

    /**
     * 查询单个外出人员列表
     *
     * @param id
     * @return
     */
    @ApiOperation("单个外出人员详情")
    @GetMapping("/leave/egressUser")
    public ResultResponse egressUser(@RequestParam Integer id) {
        Leave leave = leaveService.selectByPrimaryKey(id);
        return ResultResponse.success(leave);
    }

    /**
     * 根据姓名查询单个外出人员
     *
     * @param username
     * @return
     */
    @ApiOperation("根据姓名查询单个外出人员")
    @GetMapping("/leave/egressUserByName")
    public ResultResponse egressUserByName(@RequestParam String username) {
        Leave leave = leaveService.selectByName(username);
        return ResultResponse.success(leave);
    }

    @ApiOperation("更新外出人员信息")
    @PostMapping("/leave/updateUser")
    public ResultResponse updateUser(@Validated @RequestBody UpdateLeaveDto leaveDto) {
        Leave leaveUser = new Leave();
        BeanUtils.copyProperties(leaveDto, leaveUser);
        leaveService.update(leaveUser);
        return ResultResponse.success();
    }

    /**
     * 新增外出人员
     *
     * @param leaveDto
     * @return
     */
    @ApiOperation("新增外出人员")
    @PostMapping("/leave/addEgressUser")
    public ResultResponse addEgressUser(@Validated @RequestBody AddLeaveDto leaveDto) {
        Leave leave = new Leave();
        BeanUtils.copyProperties(leaveDto, leave);
        leaveService.addUser(leave);
        return ResultResponse.success();
    }

    /**
     * 删除外出人员记录
     *
     * @param id
     * @return
     */
    @ApiOperation("删除外出人员")
    @GetMapping("/admin/leave/deleteEgressUser/{id}")
    public ResultResponse deleteEgressUser(@PathVariable("id") Integer id) {
        leaveService.deleteEgressUser(id);
        return ResultResponse.success();
    }

    /**
     * 批量删除外出人员
     *
     * @param ids
     * @return
     */
    @ApiOperation("批量删除外出人员")
    @PostMapping("/admin/leave/batchDeleteEgressUser")
    public ResultResponse batchDeleteEgressUser(@RequestBody Integer[] ids) {
        leaveService.batchDeleteEgressUser(ids);
        return ResultResponse.success();
    }
}
