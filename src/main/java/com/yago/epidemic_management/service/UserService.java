package com.yago.epidemic_management.service;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.model.dto.add.AddUserDto;
import com.yago.epidemic_management.model.pojo.User;

/**
 * @Author: Yago
 * @Date: 2022/3/22 10:31
 * Description:
 **/
public interface UserService {
    User selectByPrimaryKey(String id);

    User selectByName(String username);

    void register(String mobile, String password);

    User login(String mobile, String password);

    boolean checkAdminRole(User user);

    boolean checkAdminRoleById(String userId);

    PageInfo userListForAdmin(Integer pageNum, Integer pageSize);

    PageInfo adminListForAdmin(Integer pageNum, Integer pageSize);

    void addUser(AddUserDto addUserDto);

    void update(User updateUser);

    void deleteUser(String userId);

    void batchDeleteEgressUser(String[] ids, Integer status);
}
