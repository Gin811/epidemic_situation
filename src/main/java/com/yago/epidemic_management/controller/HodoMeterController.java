package com.yago.epidemic_management.controller;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.common.ResultResponse;
import com.yago.epidemic_management.model.pojo.HodoMeter;
import com.yago.epidemic_management.service.HodoMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Yago
 * @Date: 2022/3/24 21:10
 * Description:
 **/
@RestController
@RequestMapping("admin/hodometer")
public class HodoMeterController {
    @Autowired
    HodoMeterService hodoMeterService;

    @PostMapping("/getHodoMeterList")
    public ResultResponse getHodoMeterList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageInfo hodeMeterList = hodoMeterService.getHodeMeterList(pageNum, pageSize);
        return ResultResponse.success(hodeMeterList);
    }

    @GetMapping("/getHodoMeter")
    public ResultResponse getHodoMeter(@RequestParam Integer id) {
        HodoMeter hodeMeter = hodoMeterService.getHodeMeter(id);
        return ResultResponse.success(hodeMeter);
    }
}
