package com.yago.epidemic_management.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.exception.ExceptionEnum;
import com.yago.epidemic_management.exception.MyException;
import com.yago.epidemic_management.model.dao.HodoMeterMapper;
import com.yago.epidemic_management.model.pojo.HodoMeter;
import com.yago.epidemic_management.service.HodoMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Yago
 * @Date: 2022/3/24 21:12
 * Description:
 **/
@Service
public class HodoMeterServiceImpl implements HodoMeterService {

    @Autowired
    HodoMeterMapper hodoMeterMapper;

    @Override
    public PageInfo getHodeMeterList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<HodoMeter> hodoMeterList = hodoMeterMapper.selectHodeMeterList();
        PageInfo pageInfo = new PageInfo(hodoMeterList);
        return pageInfo;
    }

    @Override
    public HodoMeter selectByName(String username) {
        return hodoMeterMapper.selectByName(username);
    }

    @Override
    public HodoMeter getHodeMeter(Integer id) {
        HodoMeter hodoMeter = hodoMeterMapper.selectByPrimaryKey(id);
        if (hodoMeter == null) {
            throw new MyException(ExceptionEnum.QUERY_FAILED);
        }
        return hodoMeter;
    }

    @Override
    public void addUser(HodoMeter hodoMeter) {
        int count = hodoMeterMapper.insertSelective(hodoMeter);
        if (count == 0) {
            throw new MyException(ExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public void updateUser(HodoMeter hodoMeter) {
        HodoMeter hodoMeter1 = hodoMeterMapper.selectByName(hodoMeter.getUsername());
        if (hodoMeter1 != null && !hodoMeter1.getId().equals(hodoMeter.getId())) {
            throw new MyException(ExceptionEnum.UPDATE_FAILED);
        }
        int count = hodoMeterMapper.updateByPrimaryKeySelective(hodoMeter);
        if (count == 0) {
            throw new MyException(ExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void deleteUser(Integer id) {
        HodoMeter hodoMeter = hodoMeterMapper.selectByPrimaryKey(id);
        if (hodoMeter == null) {
            throw new MyException(ExceptionEnum.NO_RECORD);
        }
        int count = hodoMeterMapper.deleteByPrimaryKey(id);
        if (count == 0) {
            throw new MyException(ExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void deleteUsers(Integer[] ids) {
        int count = hodoMeterMapper.deleteUsers(ids);
        if (count == 0) {
            throw new MyException(ExceptionEnum.DELETE_FAILED);
        }
    }
}
