package com.yago.epidemic_management.service.Impl;

import com.yago.epidemic_management.exception.ExceptionEnum;
import com.yago.epidemic_management.exception.MyException;
import com.yago.epidemic_management.model.dao.IconMapper;
import com.yago.epidemic_management.model.dto.add.AddIconDto;
import com.yago.epidemic_management.model.pojo.Icon;
import com.yago.epidemic_management.service.IconService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: YaGo
 * @Date: 2022/4/9 22:42
 * Description:
 **/
@Service
public class IconServiceIpml implements IconService {
    @Autowired
    IconMapper iconMapper;

    @Override
    public void addIcon(AddIconDto addIconDto) {
        Icon icon = new Icon();
        BeanUtils.copyProperties(addIconDto, icon);
        int count = iconMapper.insertSelective(icon);
        if (count == 0) {
            throw new MyException(ExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public Icon queryIcon(Integer userId) {
        Icon icon = iconMapper.selectByUserId(userId);
        if (icon == null) {
            throw new MyException(ExceptionEnum.NO_RECORD);
        }
        return icon;
    }
}
