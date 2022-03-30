package com.yago.epidemic_management.service;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.model.pojo.HodoMeter;

/**
 * @Author: Yago
 * @Date: 2022/3/24 21:12
 * Description:
 **/
public interface HodoMeterService {
    PageInfo getHodeMeterList(Integer pageNum, Integer pageSize);

    HodoMeter getHodeMeter(Integer id);

    void addUser(HodoMeter hodoMeter);

    void updateUser(HodoMeter hodoMeter);

    void deleteUser(Integer id);

    void deleteUsers(Integer[] ids);
}
