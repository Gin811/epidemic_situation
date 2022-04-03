package com.yago.epidemic_management.service;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.model.pojo.Leave;

/**
 * @Author: Yago
 * @Date: 2022/3/24 9:47
 * Description:
 **/
public interface LeaveService {
    PageInfo getLeaveList(Integer pageNum, Integer pageSize);

    Leave selectByName(String username);

    Leave selectByPrimaryKey(Integer id);

    void update(Leave leaveUser);

    void addUser(Leave leaveDto);

    void deleteEgressUser(Integer id);

    void batchDeleteEgressUser(Integer[] ids);
}
