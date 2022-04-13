package com.yago.epidemic_management.controller;

import com.yago.epidemic_management.common.ResultResponse;
import com.yago.epidemic_management.model.dao.GoodsMapper;
import com.yago.epidemic_management.model.pojo.Goods;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: YaGo
 * @Date: 2022/4/13 23:41
 * Description:
 **/
@RestController
public class GoodsController {

    @Autowired
    GoodsMapper goodsMapper;

    @ApiOperation("查询所有商品")
    @GetMapping("goods/getAllGoods")
    public ResultResponse getAllGoods() {
        List<Goods> goodsList = goodsMapper.getGoodsAll();
        return ResultResponse.success(goodsList);
    }
}
