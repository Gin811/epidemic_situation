package com.yago.epidemic_management.model.dao;

import com.yago.epidemic_management.model.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByName(@Param("username") String username);

    User selectLogin(@Param("username") String username, @Param("password") String md5Password);

    List<User> selectList();

    int changUserStatus(@Param("id") Integer id, @Param("status") Integer newStatus);

    int batchDeleteEgressUser(@Param("ids") Integer[] ids, @Param("status") Integer newStatus);
}