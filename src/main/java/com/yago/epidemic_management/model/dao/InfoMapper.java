package com.yago.epidemic_management.model.dao;

import com.yago.epidemic_management.model.pojo.Info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: YaGo
 * @Date: 2022/4/5 21:01
 * Description:
 **/
@Mapper
public interface InfoMapper {
    int selectByPrimaryKey(Integer id);

    List<Info> selectByUserName(String userName);

    List<Info> selectAllInfo();

    int insert(Info info);

    int insertSelective(Info info);

    int updateByPrimaryKeySelective(Info info);

    int deleteInfo(@Param("id") Integer id);

}
