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

import java.util.Date;

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

        Icon icon1 = iconMapper.selectByPrimaryKey(icon.getUserId());
        if (icon1 != null) {
            iconMapper.updateActivityByuserId(icon.getUserId());
        }
        icon.setIsActivity(1);
        if (icon.getLastUpdate() == null) {
            Date date = new Date();
            icon.setLastUpdate(date);
        }
        int count1 = iconMapper.insertSelective(icon);
        if (count1 == 0) {
            throw new MyException(ExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public Icon queryIcon(String userId) {
        Icon icon = iconMapper.selectByUserId(userId);
        if (icon == null) {
            throw new MyException(ExceptionEnum.NO_RECORD);
        }
        return icon;
    }
}
