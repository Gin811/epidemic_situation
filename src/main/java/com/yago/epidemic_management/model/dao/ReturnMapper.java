package com.yago.epidemic_management.model.dao;

import com.yago.epidemic_management.model.pojo.Return;
import com.yago.epidemic_management.model.vo.ReturnUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReturnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Return record);

    int insertSelective(Return record);

    Return selectByPrimaryKey(Integer id);

    int updateByUserIdSelective(Return record);

    int updateByPrimaryKeySelective(Return record);

    int updateByPrimaryKey(Return record);

    List<ReturnUserVo> selectAll();

    List<ReturnUserVo> selectReviewedAll();


    ReturnUserVo selectByUserName(@Param("userName") String userName);

    Return selectByUserId(@Param("userId") Integer userId);

    int dateleByUserId(@Param("userId") Integer userId);

    int batchDeleteByUserId(@Param("userIds") Integer[] userIds);
}
