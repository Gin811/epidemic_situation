package com.yago.epidemic_management.controller;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.common.ResultResponse;
import com.yago.epidemic_management.model.dto.AddRegisterDto;
import com.yago.epidemic_management.model.dto.UpdateRegisterDto;
import com.yago.epidemic_management.model.pojo.Register;
import com.yago.epidemic_management.service.RegisterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Yago
 * @Date: 2022/3/24 18:24
 * Description:     健康登记表
 **/
@CrossOrigin
@RestController
@RequestMapping("/admin/register")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @ApiOperation("健康登记人员列表")
    @GetMapping("/registerList")
    public ResultResponse getRegisterList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, String username) {

        if (username.isEmpty()) {
            PageInfo registerList = registerService.getRegisterList(pageNum, pageSize);
            return ResultResponse.success(registerList);
        } else {
            Register register = registerService.selectByName(username);
            return ResultResponse.success(register);
        }
    }

    @ApiOperation("个人健康登记详情")
    @GetMapping("/registerUser")
    public ResultResponse registerUser(@RequestParam("id") Integer id) {
        Register register = registerService.selectRegisterUser(id);
        return ResultResponse.success(register);
    }

    @ApiOperation("更新个人健康信息")
    @PostMapping("/update")
    public ResultResponse updateRegisterUser(@Validated @RequestBody UpdateRegisterDto registerDto) {
        Register registerUser = new Register();
        BeanUtils.copyProperties(registerDto, registerUser);
        registerService.updateRegisterUser(registerUser);
        return ResultResponse.success();
    }

    @ApiOperation("新增个人健康信息")
    @PostMapping("/add")
    public ResultResponse addRegisterUser(@Validated @RequestBody AddRegisterDto registerDto) {
        Register register = new Register();
        BeanUtils.copyProperties(registerDto, register);
        registerService.addRegisterUser(register);
        return ResultResponse.success();
    }

    @ApiOperation("删除个人健康信息")
    @PostMapping("delete/{id}")
    public ResultResponse deleteRegisterUser(@PathVariable("id") Integer id) {
        registerService.deleteRegisterUser(id);
        return ResultResponse.success();
    }

    @ApiOperation("批量删除个人健康信息")
    @PostMapping("batchDelete")
    public ResultResponse batchDeleteRegisterUser(@RequestParam("ids") Integer[] ids) {
        registerService.batchDeleteRegisterUser(ids);
        return ResultResponse.success();
    }
}
