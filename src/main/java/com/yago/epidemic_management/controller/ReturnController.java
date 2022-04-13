package com.yago.epidemic_management.controller;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.common.ResultResponse;
import com.yago.epidemic_management.model.dto.add.AddReturn;
import com.yago.epidemic_management.model.pojo.Return;
import com.yago.epidemic_management.model.vo.ReturnUserVo;
import com.yago.epidemic_management.service.ReturnService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: YaGo
 * @Date: 2022/4/12 9:54
 * Description:     返回控制器
 **/
@RestController
public class ReturnController {

    @Autowired
    ReturnService returnService;

    @ApiOperation("申请返回列表")
    @RequestMapping("/admin/return/query")
    public ResultResponse getReturnList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo returnList = returnService.getReturnList(pageNum, pageSize);
        return ResultResponse.success(returnList);
    }

    @ApiOperation("已审核的申请返回列表")
    @RequestMapping("/admin/return/queryReviewed")
    public ResultResponse getReviewedReturnList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo returnList = returnService.getReviewedReturnList(pageNum, pageSize);
        return ResultResponse.success(returnList);
    }

    @ApiOperation("按名查询申请返回")
    @RequestMapping("/return/queryByName")
    public ResultResponse queryByName(@RequestParam String userName) {
        ReturnUserVo user = returnService.getReturnUserByName(userName);
        return ResultResponse.success(user);
    }

    @ApiOperation("增加申请")
    @RequestMapping("/return/add")
    public ResultResponse addReturn(@Validated @RequestBody AddReturn addReturn) {
        returnService.addReturn(addReturn);
        return ResultResponse.success();
    }

    @ApiOperation("修改申请")
    @RequestMapping("/return/update")
    public ResultResponse updateReturn(@Validated @RequestBody AddReturn addReturn) {
        Return aReturn = returnService.updateReturn(addReturn);
        return ResultResponse.success(aReturn);
    }

    @ApiOperation("根据用户ID修改审核状态")
    @RequestMapping("/admin/return/updateStatus")
    public ResultResponse updateReturn(@RequestParam("userId") Integer userId) {
        returnService.updateReturnStatus(userId);
        return ResultResponse.success();
    }

    @ApiOperation("删除返回申请")
    @RequestMapping("/admin/return/delete")
    public ResultResponse deleteByUserId(@RequestParam("userId") Integer userId) {
        returnService.dateleByUserId(userId);
        return ResultResponse.success();
    }

    @ApiOperation("批量删除返回申请")
    @RequestMapping("/admin/return/batchDelete")
    public ResultResponse batchDeleteByUserId(@RequestBody Integer[] userIds) {
        returnService.batchDeleteByUserId(userIds);
        return ResultResponse.success();
    }
}
