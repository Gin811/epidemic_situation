package com.yago.epidemic_management.service;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.model.dto.add.AddReturn;
import com.yago.epidemic_management.model.pojo.Return;
import com.yago.epidemic_management.model.vo.ReturnUserVo;

/**
 * @Author: YaGo
 * @Date: 2022/4/12 9:52
 * Description:
 **/
public interface ReturnService {
    PageInfo getReturnList(Integer pageNum, Integer pageSize);

    PageInfo getReviewedReturnList(Integer pageNum, Integer pageSize);

    ReturnUserVo getReturnUserByName(String userName);

    void addReturn(AddReturn addReturn);

    Return updateReturn(AddReturn addReturn);

    void updateReturnStatus(Integer userId);

    void dateleByUserId(Integer userId);

    void batchDeleteByUserId(Integer[] userIds);
}
