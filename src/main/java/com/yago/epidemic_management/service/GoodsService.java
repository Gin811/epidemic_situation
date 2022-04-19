package com.yago.epidemic_management.service;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.model.dto.add.AddGoodsDto;
import com.yago.epidemic_management.model.dto.update.UpdateGoods;
import com.yago.epidemic_management.model.pojo.Goods;

import java.util.List;

/**
 * @Author: YaGo
 * @Date: 2022/4/13 23:42
 * Description:
 **/
public interface GoodsService {
    List<Goods> getAllGoods();

    PageInfo getAllPageGoods(Integer pageNum, Integer pageSize);

    void addGoods(AddGoodsDto addGoodsDto);

    void deleteByGoodsId(String goodsId);

    void updateByGoodsId(UpdateGoods updateGoods);
}
