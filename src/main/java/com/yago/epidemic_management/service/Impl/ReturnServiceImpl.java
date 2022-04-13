package com.yago.epidemic_management.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.exception.ExceptionEnum;
import com.yago.epidemic_management.exception.MyException;
import com.yago.epidemic_management.model.dao.ReturnMapper;
import com.yago.epidemic_management.model.dto.add.AddReturn;
import com.yago.epidemic_management.model.pojo.Return;
import com.yago.epidemic_management.model.vo.ReturnUserVo;
import com.yago.epidemic_management.service.ReturnService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YaGo
 * @Date: 2022/4/12 9:53
 * Description:
 **/
@Service
public class ReturnServiceImpl implements ReturnService {

    @Autowired
    ReturnMapper returnMapper;

    @Override
    public PageInfo getReturnList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ReturnUserVo> returns = returnMapper.selectAll();
        PageInfo<ReturnUserVo> pageInfo = new PageInfo<>(returns);
        return pageInfo;
    }

    @Override
    public PageInfo getReviewedReturnList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ReturnUserVo> returns = returnMapper.selectReviewedAll();
        PageInfo<ReturnUserVo> pageInfo = new PageInfo<>(returns);
        return pageInfo;
    }


    @Override
    public ReturnUserVo getReturnUserByName(String userName) {
        ReturnUserVo aReturn = returnMapper.selectByUserName(userName);
        if (aReturn == null) {
            throw new MyException(ExceptionEnum.NO_RECORD);
        }
        return aReturn;
    }

    @Override
    public void addReturn(AddReturn addReturn) {
        Return aReturn1 = returnMapper.selectByUserId(addReturn.getUserId());
        if (aReturn1 != null) {
            throw new MyException(ExceptionEnum.RECORD_EXITED);
        }
        Return aReturn = new Return();
        BeanUtils.copyProperties(addReturn, aReturn);
        aReturn.setStatus("未审核");
        int count = returnMapper.insertSelective(aReturn);
        if (count == 0) {
            throw new MyException(ExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public Return updateReturn(AddReturn addReturn) {
        Return aReturn = new Return();
        BeanUtils.copyProperties(addReturn, aReturn);
        int count = returnMapper.updateByUserIdSelective(aReturn);
        if (count == 0) {
            throw new MyException(ExceptionEnum.UPDATE_FAILED);
        }
        return aReturn;
    }

    @Override
    public void updateReturnStatus(Integer userId) {
        Return aReturn = returnMapper.selectByUserId(userId);
        if (aReturn == null) {
            throw new MyException(ExceptionEnum.NO_RECORD);
        }
        switch (aReturn.getStatus()) {
            case "已审核":
                aReturn.setStatus("未审核");
                break;
            case "未审核":
                aReturn.setStatus("已审核");
                break;
        }
        int count = returnMapper.updateByUserIdSelective(aReturn);
        if (count == 0) {
            throw new MyException(ExceptionEnum.UPDATE_FAILED);
        }
        return;
    }

    @Override
    public void dateleByUserId(Integer userId) {
        returnMapper.dateleByUserId(userId);
        return;
    }

    @Override
    public void batchDeleteByUserId(Integer[] userIds) {
        returnMapper.batchDeleteByUserId(userIds);
        return;
    }
}
