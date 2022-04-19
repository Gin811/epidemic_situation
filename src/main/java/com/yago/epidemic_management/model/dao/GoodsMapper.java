package com.yago.epidemic_management.model.dao;

import com.yago.epidemic_management.model.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: YaGo
 * @Date: 2022/4/13 23:36
 * Description:
 **/
@Mapper
public interface GoodsMapper {
    List<Goods> getGoodsAll();

    int insertSelective(Goods goods);

    void deleteByGoodsId(@Param("goodsId") String goodsId);

    int updateByGoodsId(Goods goods);
}
