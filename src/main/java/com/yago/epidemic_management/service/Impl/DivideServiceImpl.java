package com.yago.epidemic_management.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.exception.ExceptionEnum;
import com.yago.epidemic_management.exception.MyException;
import com.yago.epidemic_management.model.dao.DivideMapper;
import com.yago.epidemic_management.model.pojo.Divide;
import com.yago.epidemic_management.model.pojo.User;
import com.yago.epidemic_management.model.vos.ParamVo;
import com.yago.epidemic_management.service.DivideService;
import com.yago.epidemic_management.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: YaGo
 * @Date: 2022/4/19 20:22
 * Description:
 **/
@Service
public class DivideServiceImpl implements DivideService {
    @Autowired
    DivideMapper divideMapper;

    @Autowired
    UserServiceImpl userService;

    @Override
    public void addDivide(Divide divide) {
        User user = divideMapper.selectByNameMobile(divide.getUsername(), divide.getMobile());
        if (user != null) {
            divide.setUserId(user.getUserId());
        } else {
            divide.setUserId(ServiceUtil.getTimeUuid());
        }
        int count = divideMapper.insertSelective(divide);
        if (count == 0) {
            throw new MyException(ExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public PageInfo getDivideList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Divide> divides = divideMapper.selectBypage();
        PageInfo<Divide> dividePageInfo = new PageInfo<>(divides);
        return dividePageInfo;
    }

    @Override
    public Object getDivideListByCondition(Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        List<String> list = (List) map.get("searchValues");

        PageHelper.startPage(pageNum, pageSize);
        ParamVo paramVo = new ParamVo();

        if (map.get("username") != null) {
            paramVo.setUsername((String) map.get("username"));
            Divide divide = divideMapper.selectByName(paramVo.getUsername());
            if (divide == null) {
                throw new MyException(ExceptionEnum.NO_RECORD);
            }
            return divide;
        } else if (map.get("address") != null) {
            paramVo.setAddress((String) map.get("address"));
            List<Divide> divideList = divideMapper.selectByAddress(paramVo.getAddress());
            PageInfo pageInfo = new PageInfo(divideList);
            return pageInfo;
        } else if (map.get("mobile") != null) {
            paramVo.setMobile((String) map.get("mobile"));
            List<Divide> divideList = divideMapper.selectByMobile(paramVo.getMobile());
            PageInfo pageInfo = new PageInfo(divideList);
            return pageInfo;
        } else {
            if (list.contains("咳嗽")) {
                paramVo.setStatus1(1);
            } else {
                paramVo.setStatus1(0);
            }
            if (list.contains("感冒")) {
                paramVo.setStatus2(1);
            } else {
                paramVo.setStatus2(0);
            }
            if (list.contains("发烧")) {
                paramVo.setStatus3(1);
            } else {
                paramVo.setStatus3(0);
            }
            List<Divide> divideList = divideMapper.getDivideListByCondition(paramVo);
            PageInfo pageInfo = new PageInfo(divideList);
            return pageInfo;
        }
    }

    @Override
    public PageInfo getDivideHistory(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Divide> divideList = divideMapper.selectHistory();
        PageInfo<Divide> pageInfo = new PageInfo(divideList);
        return pageInfo;
    }

    @Override
    public void updateDivide(Divide divide) {
        int count = divideMapper.updateByPrimaryKeySelective(divide);
        if (count == 0) {
            throw new MyException(ExceptionEnum.UPDATE_FAILED);
        }
    }


    @Override
    public void deleteByUserId(String userId) {
        divideMapper.deleteByUserId(userId);
    }


}
