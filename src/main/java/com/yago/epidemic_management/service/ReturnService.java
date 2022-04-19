package com.yago.epidemic_management.service;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.model.dto.add.AddReturn;
import com.yago.epidemic_management.model.dto.update.UpdateReturn;
import com.yago.epidemic_management.model.pojo.Return;

/**
 * @Author: YaGo
 * @Date: 2022/4/12 9:52
 * Description:
 **/
public interface ReturnService {
    PageInfo getReturnList(Integer pageNum, Integer pageSize);

    PageInfo getReviewedReturnList(Integer pageNum, Integer pageSize);

    Return getReturnUserByName(String userName);

    void addReturn(AddReturn addReturn);

    Return updateReturn(UpdateReturn updateReturn);

    void updateReturnStatus(Integer userId);

    void deleteById(Integer userId);

    void batchDeleteById(Integer[] ids);
}
