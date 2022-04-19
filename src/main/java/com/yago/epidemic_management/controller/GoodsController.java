package com.yago.epidemic_management.controller;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.common.ResultResponse;
import com.yago.epidemic_management.model.dto.add.AddGoodsDto;
import com.yago.epidemic_management.model.dto.update.UpdateGoods;
import com.yago.epidemic_management.model.pojo.Goods;
import com.yago.epidemic_management.service.GoodsService;
import com.yago.epidemic_management.service.OssService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.yago.epidemic_management.common.Constant.goodsUpLoad;

/**
 * @Author: YaGo
 * @Date: 2022/4/13 23:41
 * Description:
 **/
@RestController
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OssService ossService;

    @ApiOperation("查询所有商品")
    @GetMapping("/getAllGoods")
    public ResultResponse getAllGoods() {
        List<Goods> goodsList = goodsService.getAllGoods();
        return ResultResponse.success(goodsList);
    }

    @ApiOperation("分页查询所有商品")
    @GetMapping("/goods/getAllPageGoods")
    public ResultResponse getAllPageGoods(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageInfo allPageGoods = goodsService.getAllPageGoods(pageNum, pageSize);
        return ResultResponse.success(allPageGoods);
    }

    @ApiOperation("增加商品")
    @PostMapping("/goods/add")
    public ResultResponse addGoods(@RequestBody AddGoodsDto goodsDto) {

        goodsService.addGoods(goodsDto);
        return ResultResponse.success();
    }

    @ApiOperation("删除单个商品")
    @GetMapping("/goods/deleteByGoodsId")
    public ResultResponse deleteByGoodsId(@RequestParam("goodsId") String goodsId) {
        goodsService.deleteByGoodsId(goodsId);
        return ResultResponse.success();
    }

    @ApiOperation("修改单个商品")
    @PostMapping("/goods/updateByGoodsId")
    public ResultResponse updateByGoodsId(@RequestBody UpdateGoods updateGoods) {
        goodsService.updateByGoodsId(updateGoods);
        return ResultResponse.success();
    }

    // OOS单文件上传
    @ApiOperation("OOS单文件上传")
    @RequestMapping(value = ("/goods/oosFile/upload"), headers = ("content-type=multipart/*"), method = RequestMethod.POST)
    public Object OOSFileUpload(@RequestParam("file") MultipartFile multipartFile) {
        String path = ossService.uploadFile(multipartFile);
        Map<String, Object> map = new HashMap();
        map.put("status", 200);
        map.put("msg", "成功");
        map.put("data", path);
        return map;
    }


    //本机上传图片
    @ApiOperation("本机单文件上传")
    @RequestMapping(value = ("/goods/file/upload"), headers = ("content-type=multipart/*"), method = RequestMethod.POST)
    public Object fileUpload(@RequestParam("file") MultipartFile multipartFile) {

        Map<String, Object> map = new HashMap();
        map.put("status", 10000);
        map.put("msg", "上传成功");

        if (multipartFile.isEmpty()) {
            map.put("status", 10019);
            map.put("msg", "空文件");
            map.put("data", null);
            return map;
        }

        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        if ("".equals(fileName)) {
            map.put("status", 10020);
            map.put("msg", "文件名不能为空");
            map.put("data", null);
            return map;
        }
        System.out.println("上传文件原始的名字: " + fileName);

        //获取原始名字后缀
        String subName = fileName.substring(fileName.lastIndexOf("."));
        // 使用uuid生成新文件名
        UUID uuid = UUID.randomUUID();

        String newFileName = uuid + subName;
        System.out.println("保存的文件的新名字: " + newFileName);

        //保存在goods文件夹
        String format = "goods";

        // 生成文件路径
        File readPath = new File(goodsUpLoad + File.separator + format);
        System.out.println("存放的文件夹: " + readPath);
        System.out.println("存放文件的绝对路径: " + readPath.getAbsolutePath());
        // 判断文件夹是否存在
        if (!readPath.isDirectory()) {
            // 创建文件夹
            readPath.mkdirs();
        }

        // 文件真实的保存路径
        File file = new File(readPath.getAbsolutePath() + File.separator + newFileName);
        try {
            multipartFile.transferTo(file);

            String filePath = "/" + format + "/" + newFileName;
            map.put("path", filePath);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("status", 10021);
            map.put("msg", "上传失败");
            map.put("data", null);
        }
        return map;
    }
}
