package com.yago.epidemic_management.controller;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.common.ResultResponse;
import com.yago.epidemic_management.model.dto.QueryInfoDto;
import com.yago.epidemic_management.model.dto.update.UpdateInfoDto;
import com.yago.epidemic_management.model.pojo.Info;
import com.yago.epidemic_management.service.InfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: YaGo
 * @Date: 2022/4/5 20:50
 * Description:
 **/
@RestController
public class InfoController {

    @Autowired
    InfoService infoService;

    /**
     * 查询所有通知信息
     *
     * @param queryInfo
     * @return
     */
    @ApiOperation("查询所有通知")
    @PostMapping("/info/queryInfo")
    public ResultResponse queryInfo(@RequestBody QueryInfoDto queryInfo) {
        PageInfo pageInfo = infoService.selectInfoList(queryInfo);
        return ResultResponse.success(pageInfo);
    }

    /**
     * 根据用户名查询通知
     *
     * @param queryInfo
     * @return
     */
    @ApiOperation("根据用户名查询通知")
    @PostMapping("/info/queryInfoByName")
    public ResultResponse queryInfoByName(@RequestBody QueryInfoDto queryInfo) {
        PageInfo pageInfo = infoService.selectByName(queryInfo);
        return ResultResponse.success(pageInfo);
    }

    /**
     * 更新所有通知
     *
     * @param updateInfo
     * @return
     */
    @ApiOperation("更新通知")
    @PostMapping("/admin/Info/update")
    public ResultResponse updateInfo(@RequestBody UpdateInfoDto updateInfo) {
        System.out.println(updateInfo.toString());
        Info info = new Info();
        BeanUtils.copyProperties(updateInfo, info);
        infoService.updateInfo(info);
        return ResultResponse.success();
    }

    /**
     * 根据ID删除通知
     *
     * @param id
     * @return
     */
    @ApiOperation("删除通知")
    @GetMapping("/admin/Info/delete/{id}")
    public ResultResponse deleteById(@PathVariable Integer id) {
        infoService.delete(id);
        return ResultResponse.success();
    }

    /**
     * 新增通知
     *
     * @param queryInfoDto
     * @return
     */
    @ApiOperation("新增通知")
    @PostMapping("/admin/Info/addInfo")
    public ResultResponse addInfo(@RequestBody QueryInfoDto queryInfoDto) {
        Info info = new Info();
        BeanUtils.copyProperties(queryInfoDto, info);
        infoService.addInfo(info);
        return ResultResponse.success();
    }
}
