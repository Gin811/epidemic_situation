package com.yago.epidemic_management.controller;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.common.ResultResponse;
import com.yago.epidemic_management.model.dto.QueryInfoDto;
import com.yago.epidemic_management.model.dto.UpdateInfoDto;
import com.yago.epidemic_management.model.pojo.Info;
import com.yago.epidemic_management.service.InfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: YaGo
 * @Date: 2022/4/5 20:50
 * Description:
 **/
@RestController
@RequestMapping("/admin/Info")
public class InfoController {

    @Autowired
    InfoService infoService;

    @PostMapping("/queryInfo")
    public ResultResponse queryInfo(@RequestBody QueryInfoDto queryInfo) {
        PageInfo pageInfo = infoService.selectInfoList(queryInfo);
        return ResultResponse.success(pageInfo);
    }

    @PostMapping("/queryInfoByName")
    public ResultResponse queryInfoByName(@RequestBody QueryInfoDto queryInfo) {
        PageInfo pageInfo = infoService.selectByName(queryInfo);
        return ResultResponse.success(pageInfo);
    }

    @PostMapping("/update")
    public ResultResponse updateInfo(@RequestBody UpdateInfoDto updateInfo) {
        System.out.println(updateInfo.toString());
        Info info = new Info();
        BeanUtils.copyProperties(updateInfo, info);
        infoService.updateInfo(info);
        return ResultResponse.success();
    }

    @GetMapping("/delete/{id}")
    public ResultResponse deleteById(@PathVariable Integer id) {
        infoService.delete(id);
        return ResultResponse.success();
    }

    @PostMapping("/addInfo")
    public ResultResponse addInfo(@RequestBody QueryInfoDto queryInfoDto) {
        Info info = new Info();
        BeanUtils.copyProperties(queryInfoDto, info);
        infoService.addInfo(info);
        return ResultResponse.success();
    }
}
