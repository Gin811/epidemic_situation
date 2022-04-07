package com.yago.epidemic_management.controller;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.common.ResultResponse;
import com.yago.epidemic_management.model.dto.AddHodoMeter;
import com.yago.epidemic_management.model.dto.UpdateHodoMeter;
import com.yago.epidemic_management.model.pojo.HodoMeter;
import com.yago.epidemic_management.service.HodoMeterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Yago
 * @Date: 2022/3/24 21:10
 * Description:  外出历史记录
 **/
@CrossOrigin
@RestController
@RequestMapping("admin/hodometer")
public class HodoMeterController {
    @Autowired
    HodoMeterService hodoMeterService;

    @ApiOperation("所有外出信息列表")
    @GetMapping("/getHodoMeterList")
    public ResultResponse getHodoMeterList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, String username) {

        if (username.isEmpty()) {
            PageInfo hodeMeterList = hodoMeterService.getHodeMeterList(pageNum, pageSize);
            return ResultResponse.success(hodeMeterList);
        } else {
            HodoMeter hodoMeter = hodoMeterService.selectByName(username);
            System.out.println(hodoMeter.getCx_time());
            return ResultResponse.success(hodoMeter);
        }
    }

    @ApiOperation("单人外出信息详情")
    @GetMapping("/getHodoMeter")
    public ResultResponse getHodoMeter(@RequestParam Integer id) {
        HodoMeter hodeMeter = hodoMeterService.getHodeMeter(id);
        return ResultResponse.success(hodeMeter);
    }

    @ApiOperation("添加个人外出信息")
    @PostMapping("/add")
    public ResultResponse addHodoMeterUser(@Validated @RequestBody AddHodoMeter addHodoMeter) {
        HodoMeter hodoMeter = new HodoMeter();
        BeanUtils.copyProperties(addHodoMeter, hodoMeter);
        hodoMeterService.addUser(hodoMeter);
        return ResultResponse.success();
    }

    @ApiOperation("更新个人外出信息")
    @PostMapping("/update")
    public ResultResponse updateHodoMeterUser(@Validated @RequestBody UpdateHodoMeter updateHodoMeter) {
        HodoMeter hodoMeter = new HodoMeter();
        BeanUtils.copyProperties(updateHodoMeter, hodoMeter);
        hodoMeterService.updateUser(hodoMeter);
        return ResultResponse.success();
    }

    @ApiOperation("删除个人外出信息")
    @GetMapping("delete/{id}")
    public ResultResponse deteleUser(@PathVariable Integer id) {
        hodoMeterService.deleteUser(id);
        return ResultResponse.success();
    }

    @ApiOperation("批量删除个人外出信息")
    @GetMapping("deleteUsers")
    public ResultResponse batchDeleteHodoMeterUser(@RequestParam("ids") Integer[] ids) {
        hodoMeterService.deleteUsers(ids);
        return ResultResponse.success();
    }
}
