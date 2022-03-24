package com.yago.epidemic_management.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.exception.ExceptionEnum;
import com.yago.epidemic_management.exception.MyException;
import com.yago.epidemic_management.model.dao.LeaveMapper;
import com.yago.epidemic_management.model.pojo.Leave;
import com.yago.epidemic_management.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Yago
 * @Date: 2022/3/24 9:47
 * Description:
 **/
@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    LeaveMapper leaveMapper;

    /**
     * 获取外出人员列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo getLeaveList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Leave> leaveList = leaveMapper.selectLeaveList();
        PageInfo pageInfo = new PageInfo(leaveList);
        return pageInfo;
    }

    /**
     * 获取外出人员详情
     *
     * @param id
     * @return
     */
    @Override
    public Leave selectByPrimaryKey(Integer id) {
        Leave leave = leaveMapper.selectByPrimaryKey(id);
        if (leave == null) {
            throw new MyException(ExceptionEnum.QUERY_FAILED);
        }
        return leave;
    }

    /**
     * 更新外出人员详情
     *
     * @param leaveUser
     */
    @Override
    public void update(Leave leaveUser) {
        Leave oldLeave = leaveMapper.selectByName(leaveUser.getUsername());
        //如果同名，但是不同id的不能更新
        if (oldLeave != null && !oldLeave.getId().equals(leaveUser.getId())) {
            throw new MyException(ExceptionEnum.NAME_EXISTED);
        } else {
            int count = leaveMapper.updateByPrimaryKey(leaveUser);
            if (count == 0) {
                throw new MyException(ExceptionEnum.UPDATE_FAILED);
            }
        }
    }

    /**
     * 添加外出人员
     *
     * @param leave
     */
    @Override
    public void addUser(Leave leave) {
        int count = leaveMapper.insertSelective(leave);
        if (count == 0) {
            throw new MyException(ExceptionEnum.INSERT_FAILED);
        }
    }

    /**
     * 删除外出人员记录
     *
     * @param id
     * @return
     */
    @Override
    public void deleteEgressUser(Integer id) {
        Leave leave = leaveMapper.selectByPrimaryKey(id);
        //查不到该记录，无法删除
        if (leave == null) {
            throw new MyException(ExceptionEnum.NO_RECORD);
        }
        int count = leaveMapper.deleteByPrimaryKey(id);
        if (count == 0) {
            throw new MyException(ExceptionEnum.DELETE_FAILED);
        }
    }

    /**
     * 批量删除外出人员
     *
     * @param ids
     */
    @Override
    public void batchDeleteEgressUser(Integer[] ids) {
        int count = leaveMapper.batchDeleteEgressUser(ids);
        if (count == 0) {
            throw new MyException(ExceptionEnum.DELETE_FAILED);
        }
    }
}
