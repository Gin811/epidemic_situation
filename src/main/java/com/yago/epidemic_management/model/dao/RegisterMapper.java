package com.yago.epidemic_management.model.dao;

import com.yago.epidemic_management.model.pojo.Register;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RegisterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Register record);

    int insertSelective(Register record);

    Register selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Register record);

    int updateByPrimaryKey(Register record);

    List<Register> selectRegisterList();

    Register selectByName(@Param("name") String username);

    int batchDelete(@Param("ids") Integer[] ids);
}
