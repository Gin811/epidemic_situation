package com.yago.epidemic_management.model.dao;

import com.yago.epidemic_management.model.pojo.Code;
import com.yago.epidemic_management.model.vos.CodeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Code record);

    int insertSelective(Code record);

    Code selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Code record);

    List<CodeVo> selectByName(String userName);

    int updateByPrimaryKey(Code record);

    List<CodeVo> selectAllCodeList();

}
