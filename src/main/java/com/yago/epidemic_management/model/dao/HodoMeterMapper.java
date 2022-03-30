package com.yago.epidemic_management.model.dao;

import com.yago.epidemic_management.model.pojo.HodoMeter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HodoMeterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HodoMeter record);

    int insertSelective(HodoMeter record);

    HodoMeter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HodoMeter record);

    int updateByPrimaryKey(HodoMeter record);

    List<HodoMeter> selectHodeMeterList();

    HodoMeter selectName(@Param("username") String username);

    int deleteUsers(@Param("ids") Integer[] ids);
}
