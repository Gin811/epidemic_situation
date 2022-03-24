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
    public HodoMeter getHodeMeter(Integer id) {
        HodoMeter hodoMeter = hodoMeterMapper.selectByPrimaryKey(id);
        if (hodoMeter == null) {
            throw new MyException(ExceptionEnum.QUERY_FAILED);
        }
        return hodoMeter;
    }
}
