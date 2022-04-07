package com.yago.epidemic_management.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.exception.ExceptionEnum;
import com.yago.epidemic_management.exception.MyException;
import com.yago.epidemic_management.model.dao.RegisterMapper;
import com.yago.epidemic_management.model.pojo.Register;
import com.yago.epidemic_management.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Yago
 * @Date: 2022/3/24 18:31
 * Description:
 **/
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    RegisterMapper registerMapper;

    @Override
    public PageInfo getRegisterList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Register> registerList = registerMapper.selectRegisterList();
        PageInfo pageInfo = new PageInfo(registerList);
        return pageInfo;
    }

    @Override
    public Register selectByName(String username) {
        return registerMapper.selectByName(username);
    }

    @Override
    public Register selectRegisterUser(Integer id) {
        Register register = registerMapper.selectByPrimaryKey(id);
        if (register == null) {
            throw new MyException(ExceptionEnum.QUERY_FAILED);
        }
        return register;
    }

    @Override
    public void updateRegisterUser(Register registerUser) {
        //如果同名，但是不同id的不能更新
        Register register = registerMapper.selectByName(registerUser.getName());
        if (register.getId() != registerUser.getId() && register != null) {
            throw new MyException(ExceptionEnum.NAME_EXISTED);
        } else {
            int count = registerMapper.updateByPrimaryKeySelective(registerUser);
            if (count == 0) {
                throw new MyException(ExceptionEnum.UPDATE_FAILED);
            }
        }
    }

    @Override
    public void addRegisterUser(Register register) {
        int count = registerMapper.insertSelective(register);
        if (count == 0) {
            throw new MyException(ExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public void deleteRegisterUser(Integer id) {
        Register register = registerMapper.selectByPrimaryKey(id);
        //查不到该记录，无法删除
        if (register == null) {
            throw new MyException(ExceptionEnum.NO_RECORD);
        }
        int count = registerMapper.deleteByPrimaryKey(id);
        if (count == 0) {
            throw new MyException(ExceptionEnum.DELETE_FAILED);
        }
    }

    @Override
    public void batchDeleteRegisterUser(Integer[] ids) {
        int count = registerMapper.batchDelete(ids);
        if (count == 0) {
            throw new MyException(ExceptionEnum.DELETE_FAILED);
        }
    }
}
