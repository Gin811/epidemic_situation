package com.yago.epidemic_management.service;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.model.dto.QueryInfoDto;
import com.yago.epidemic_management.model.pojo.Info;

/**
 * @Author: YaGo
 * @Date: 2022/4/5 20:58
 * Description:
 **/
public interface InfoService {

    PageInfo selectInfoList(QueryInfoDto queryInfo);

    PageInfo selectByName(QueryInfoDto queryInfo);

    void updateInfo(Info info);

    void addInfo(Info info);

    void delete(Integer id);
}
