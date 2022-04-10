package com.yago.epidemic_management.service;

import com.yago.epidemic_management.model.dto.add.AddIconDto;
import com.yago.epidemic_management.model.pojo.Icon;

/**
 * @Author: YaGo
 * @Date: 2022/4/9 22:41
 * Description:
 **/
public interface IconService {
    void addIcon(AddIconDto addIconDto);

    Icon queryIcon(Integer userId);
}
