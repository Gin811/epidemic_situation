package com.yago.epidemic_management.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.exception.ExceptionEnum;
import com.yago.epidemic_management.exception.MyException;
import com.yago.epidemic_management.model.dao.CodeMapper;
import com.yago.epidemic_management.model.dto.add.AddCode;
import com.yago.epidemic_management.model.pojo.Code;
import com.yago.epidemic_management.model.vos.CodeVo;
import com.yago.epidemic_management.service.CodeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YaGo
 * @Date: 2022/4/18 20:25
 * Description:
 **/
@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    CodeMapper codeMapper;

    @Override
    public PageInfo getAllCodeList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CodeVo> codeList = codeMapper.selectAllCodeList();
        PageInfo<CodeVo> pageInfo = new PageInfo<>(codeList);
        return pageInfo;
    }

    @Override
    public void addCode(AddCode addCode) {
        Code code = new Code();
        BeanUtils.copyProperties(addCode, code);
        code.setStatus("未审核");
        int count = codeMapper.insertSelective(code);
        if (count == 0) {
            throw new MyException(ExceptionEnum.INSERT_FAILED);
        }
        return;
    }

    @Override
    public void changStatus(Code code) {
        int count = codeMapper.updateByPrimaryKeySelective(code);
        if (count == 0) {
            throw new MyException(ExceptionEnum.UPDATE_FAILED);
        }
    }


    @Override
    public List<CodeVo> searchByName(String userName) {
        List<CodeVo> codeVoList = codeMapper.selectByName(userName);
        return codeVoList;
    }


    @Override
    public void deleteCode(Integer id) {
        int count = codeMapper.deleteByPrimaryKey(id);
        if (count == 0) {
            throw new MyException(ExceptionEnum.DELETE_FAILED);
        }
    }
}
