package com.yago.epidemic_management.controller;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.common.ResultResponse;
import com.yago.epidemic_management.model.dto.add.AddCode;
import com.yago.epidemic_management.model.pojo.Code;
import com.yago.epidemic_management.model.vos.CodeVo;
import com.yago.epidemic_management.service.CodeService;
import com.yago.epidemic_management.service.OssService;
import com.yago.epidemic_management.utils.JWTUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: YaGo
 * @Date: 2022/4/18 20:25
 * Description:
 **/
@RestController
public class CodeController {
    @Autowired
    CodeService codeService;

    @Autowired
    OssService ossService;

    @ApiOperation("得到所有健康码列表")
    @GetMapping("admin/code/getAllCodeList")
    public ResultResponse getAllCodeList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {

        PageInfo codeList = codeService.getAllCodeList(pageNum, pageSize);
        return ResultResponse.success(codeList);
    }

    @ApiOperation("上传码的图片")
    @RequestMapping(value = ("/code/oosFile/upload"), headers = ("content-type=multipart/*"), method = RequestMethod.POST)
    public Object OOSFileUpload(@RequestParam("file") MultipartFile multipartFile) {
        Map<String, Object> map = new HashMap();
        String path = ossService.uploadFile(multipartFile);
        map.put("code", 200);
        map.put("msg", "上传成功");
        map.put("data", path);
        return map;
    }

    @ApiOperation("新增码")
    @PostMapping("/code/add")
    public ResultResponse addCode(@RequestBody AddCode addCode, HttpServletRequest request) {
        String token = request.getHeader("token");
        String token1 = JWTUtils.validateToken(token);
        addCode.setUserId(token1);
        codeService.addCode(addCode);
        return ResultResponse.success();
    }

    @ApiOperation("更新码的状态")
    @PostMapping("/code/updateStatus")
    public ResultResponse changeStatus(@RequestBody Code code) {
        codeService.changStatus(code);
        return ResultResponse.success();
    }

    @ApiOperation("根据用户名查信息")
    @PostMapping("/code/search")
    public ResultResponse searchByName(@RequestParam String userName) {
        List<CodeVo> codeVoList = codeService.searchByName(userName);
        return ResultResponse.success(codeVoList);
    }

    @ApiOperation("删除码")
    @GetMapping("/code/delete")
    public ResultResponse deleteCode(@RequestParam("id") Integer id) {
        codeService.deleteCode(id);
        return ResultResponse.success();
    }
}
