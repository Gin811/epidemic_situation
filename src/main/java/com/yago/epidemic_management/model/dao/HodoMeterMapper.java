package com.yago.epidemic_management.model.dao;

import com.yago.epidemic_management.model.pojo.HodoMeter;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HodoMeterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HodoMeter record);

    int insertSelective(HodoMeter record);

    HodoMeter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HodoMeter record);

    int updateByPrimaryKey(HodoMeter record);
}