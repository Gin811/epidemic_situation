package com.yago.epidemic_management.model.dao;

import com.yago.epidemic_management.model.pojo.Return;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReturnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Return record);

    int insertSelective(Return record);

    Return selectByPrimaryKey(Integer id);

    int updateByIdSelective(Return record);

    int updateByPrimaryKeySelective(Return record);

    int updateByPrimaryKey(Return record);

    List<Return> selectAll();

    List<Return> selectReviewedAll();


    Return selectByUserName(@Param("userName") String userName);

    Return selectByPhone(@Param("mobile") String mobile);

    Return selectById(@Param("id") Integer id);

    int deleteById(@Param("id") Integer id);

    int batchDeleteById(@Param("ids") Integer[] ids);
}
