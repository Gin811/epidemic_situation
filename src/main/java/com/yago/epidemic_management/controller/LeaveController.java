package com.yago.epidemic_management.controller;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.common.ResultResponse;
import com.yago.epidemic_management.model.dto.AddLeaveDto;
import com.yago.epidemic_management.model.dto.UpdateLeaveDto;
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
@RequestMapping("/admin/leave")
public class LeaveController {
    @Autowired
    LeaveService leaveService;

    /**
     * 查询外出人员列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("外出人员列表")
    @PostMapping("/egresslist")
    public ResultResponse getLeaveList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageInfo leaveList = leaveService.getLeaveList(pageNum, pageSize);
        return ResultResponse.success(leaveList);
    }

    /**
     * 查询单个外出人员列表
     *
     * @param id
     * @return
     */
    @ApiOperation("单个外出人员详情")
    @GetMapping("/egressUser")
    public ResultResponse egressUser(@RequestParam Integer id) {
        Leave leave = leaveService.selectByPrimaryKey(id);
        return ResultResponse.success(leave);
    }

    @ApiOperation("更新外出人员信息")
    @PostMapping("/updateUser")
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
    @PostMapping("/addEgressUser")
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
    @PostMapping("/deleteEgressUser")
    public ResultResponse deleteEgressUser(@RequestParam("id") Integer id) {
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
    @PostMapping("/batchDeleteEgressUser")
    public ResultResponse batchDeleteEgressUser(@RequestParam("ids") Integer[] ids) {
        leaveService.batchDeleteEgressUser(ids);
        return ResultResponse.success();
    }
}
