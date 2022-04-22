package com.yago.epidemic_management.service;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.model.pojo.Divide;

import java.util.Map;

/**
 * @Author: YaGo
 * @Date: 2022/4/19 20:21
 * Description:
 **/
public interface DivideService {
    void addDivide(Divide divide);

    PageInfo getDivideList(Integer pageNum, Integer pageSize);

    Object getDivideListByCondition(Map<String, Object> map);

    void deleteByUserId(String userId);
}
