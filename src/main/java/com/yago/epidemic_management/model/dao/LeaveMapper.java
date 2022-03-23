package com.yago.epidemic_management.model.dao;

import com.yago.epidemic_management.model.pojo.Leave;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeaveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Leave record);

    int insertSelective(Leave record);

    Leave selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Leave record);

    int updateByPrimaryKey(Leave record);
}