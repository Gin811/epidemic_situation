package com.yago.epidemic_management.controller;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.common.ResultResponse;
import com.yago.epidemic_management.model.pojo.Divide;
import com.yago.epidemic_management.service.DivideService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: YaGo
 * @Date: 2022/4/19 20:21
 * Description:
 **/
@RestController
public class DivideController {
    @Autowired
    DivideService divideService;

    @ApiOperation("新增隔离人员")
    @PostMapping("/divide/add")
    public ResultResponse addDivide(@RequestBody Divide divide) {
        divideService.addDivide(divide);
        return ResultResponse.success();
    }

    @ApiOperation("所有隔离人员列表")
    @GetMapping("/admin/divide/getDivideList")
    public ResultResponse getDivideList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageInfo divideList = divideService.getDivideList(pageNum, pageSize);
        return ResultResponse.success(divideList);
    }

    @ApiOperation("所有隔离人员历史")
    @GetMapping("/admin/divide/getDivideHistory")
    public ResultResponse getDivideHistory(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageInfo divideList = divideService.getDivideHistory(pageNum, pageSize);
        return ResultResponse.success(divideList);
    }

    @ApiOperation("根据条件查询隔离人员列表")
    @PostMapping("/admin/divide/getDivideListByCondition")
    public ResultResponse getDivideListByCondition(@RequestBody Map<String, Object> map) {
        Object object = divideService.getDivideListByCondition(map);
        if (object instanceof PageInfo) {
            return ResultResponse.success((PageInfo) object);
        } else {
            return ResultResponse.success((Divide) object);
        }
    }

    @ApiOperation("根据用户id更新隔离人员")
    @PostMapping("/divide/updateDivide")
    public ResultResponse updateDivide(@RequestBody Divide divide) {
        divideService.updateDivide(divide);
        return ResultResponse.success();
    }

    @ApiOperation("根据用户id删除隔离人员列表")
    @GetMapping("/admin/divide/deleteByUserId")
    public ResultResponse deleteByUserId(@RequestParam("userId") String userId) {
        divideService.deleteByUserId(userId);
        return ResultResponse.success();
    }
}
