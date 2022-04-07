package com.yago.epidemic_management.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.exception.ExceptionEnum;
import com.yago.epidemic_management.exception.MyException;
import com.yago.epidemic_management.model.dao.InfoMapper;
import com.yago.epidemic_management.model.dto.QueryInfoDto;
import com.yago.epidemic_management.model.pojo.Info;
import com.yago.epidemic_management.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YaGo
 * @Date: 2022/4/5 20:58
 * Description:
 **/
@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    InfoMapper infoMapper;

    @Override
    public PageInfo selectInfoList(QueryInfoDto queryInfo) {
        PageHelper.startPage(queryInfo.getPageNum(), queryInfo.getPageSize());
        List<Info> infos = infoMapper.selectAllInfo();
        PageInfo pageInfo = new PageInfo(infos);
        return pageInfo;
    }

    @Override
    public PageInfo selectByName(QueryInfoDto queryInfo) {
        PageHelper.startPage(queryInfo.getPageNum(), queryInfo.getPageSize());
        List<Info> infos = infoMapper.selectByUserName(queryInfo.getUserName());
        PageInfo pageInfo = new PageInfo(infos);
        return pageInfo;
    }

    @Override
    public void updateInfo(Info info) {
        int count = infoMapper.updateByPrimaryKeySelective(info);
        if (count == 0) {
            throw new MyException(ExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void addInfo(Info info) {
        int conut = infoMapper.insertSelective(info);
        if (conut == 0) {
            throw new MyException(ExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public void delete(Integer id) {
        int count = infoMapper.deleteInfo(id);
        if (count == 0) {
            throw new MyException(ExceptionEnum.DELETE_FAILED);
        }
    }
}
