package com.yago.epidemic_management.service;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.model.pojo.User;

/**
 * @Author: Yago
 * @Date: 2022/3/22 10:31
 * Description:
 **/
public interface UserService {
    User selectByPrimaryKey(Integer id);

    void register(String userName, String password);

    User login(String userName, String password);

    boolean checkAdminRole(User user);

    PageInfo userListForAdmin(Integer pageNum, Integer pageSize);

    void update(User updateUser);

    void changUserStatus(Integer id, Integer newId);
}
