package com.yago.epidemic_management.controller;

import com.yago.epidemic_management.common.ResultResponse;
import com.yago.epidemic_management.model.dos.SevenDayDo;
import com.yago.epidemic_management.service.BigDataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: YaGo
 * @Date: 2022/4/15 13:00
 * Description:
 **/
@RestController
@RequestMapping("/bigData")
public class BigDataController {
    @Autowired
    BigDataService bigDataService;

    @ApiOperation("查询近七天离开人数")
    @GetMapping("/selectByDayLeaveAll")
    public ResultResponse selectByDayLeaveAll() {
        List<SevenDayDo> list = bigDataService.selectByDayLeaveAll();
        return ResultResponse.success(list);
    }

    @ApiOperation("查询近七天返回人数")
    @GetMapping("/selectByDayReturnAll")
    public ResultResponse selectByDayReturnAll() {
        List<SevenDayDo> list = bigDataService.selectByDayReturnAll();
        return ResultResponse.success(list);
    }

    @ApiOperation("查询每栋住户数量")
    @GetMapping("/selectCountHouse")
    public ResultResponse selectCountHouse() {
        List<Map> maps = bigDataService.selectCountHouse();
        return ResultResponse.success(maps);
    }

    @ApiOperation("按类型统计隔离人数")
    @GetMapping("/selectDivide")
    public ResultResponse selectDivide() {
        Map map = bigDataService.selectDivide();
        return ResultResponse.success(map);
    }
}
