package com.yago.epidemic_management.model.dao;

import com.yago.epidemic_management.model.pojo.Leave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LeaveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Leave record);

    int insertSelective(Leave record);

    Leave selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Leave record);

    int updateByPrimaryKey(Leave record);

    List<Leave> selectLeaveList();

    List<Leave> selectApplications();

    Leave selectByName(String username);

    int batchDeleteEgressUser(@Param("ids") Integer[] ids);

}
