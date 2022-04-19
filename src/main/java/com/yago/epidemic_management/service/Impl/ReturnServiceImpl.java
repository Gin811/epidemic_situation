package com.yago.epidemic_management.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.exception.ExceptionEnum;
import com.yago.epidemic_management.exception.MyException;
import com.yago.epidemic_management.model.dao.ReturnMapper;
import com.yago.epidemic_management.model.dto.add.AddReturn;
import com.yago.epidemic_management.model.dto.update.UpdateReturn;
import com.yago.epidemic_management.model.pojo.Return;
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
        List<Return> returns = returnMapper.selectAll();
        PageInfo<Return> pageInfo = new PageInfo<>(returns);
        return pageInfo;
    }

    @Override
    public PageInfo getReviewedReturnList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Return> returns = returnMapper.selectReviewedAll();
        PageInfo<Return> pageInfo = new PageInfo<>(returns);
        return pageInfo;
    }


    @Override
    public Return getReturnUserByName(String userName) {
        Return aReturn = returnMapper.selectByUserName(userName);
        if (aReturn == null) {
            throw new MyException(ExceptionEnum.NO_RECORD);
        }
        return aReturn;
    }

    @Override
    public void addReturn(AddReturn addReturn) {
        Return aReturn1 = returnMapper.selectByPhone(addReturn.getMobile());
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
    public Return updateReturn(UpdateReturn updateReturn) {
        Return aReturn = new Return();
        BeanUtils.copyProperties(updateReturn, aReturn);
        int count = returnMapper.updateByIdSelective(aReturn);
        if (count == 0) {
            throw new MyException(ExceptionEnum.UPDATE_FAILED);
        }
        return aReturn;
    }

    @Override
    public void updateReturnStatus(Integer id) {
        Return aReturn = returnMapper.selectById(id);
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
        int count = returnMapper.updateByIdSelective(aReturn);
        if (count == 0) {
            throw new MyException(ExceptionEnum.UPDATE_FAILED);
        }
        return;
    }

    @Override
    public void deleteById(Integer userId) {
        returnMapper.deleteById(userId);
        return;
    }

    @Override
    public void batchDeleteById(Integer[] ids) {
        returnMapper.batchDeleteById(ids);
        return;
    }
}
