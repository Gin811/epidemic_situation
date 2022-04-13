package com.yago.epidemic_management.service.Impl;

import com.yago.epidemic_management.model.dao.GoodsMapper;
import com.yago.epidemic_management.model.pojo.Goods;
import com.yago.epidemic_management.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: YaGo
 * @Date: 2022/4/13 23:43
 * Description:
 **/
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    public List<Goods> getAllGoods() {
        List<Goods> goodsList = goodsMapper.getGoodsAll();
        return goodsList;
    }
}
