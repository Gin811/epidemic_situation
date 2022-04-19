package com.yago.epidemic_management.service;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.model.dto.add.AddCode;
import com.yago.epidemic_management.model.pojo.Code;
import com.yago.epidemic_management.model.vos.CodeVo;

import java.util.List;

/**
 * @Author: YaGo
 * @Date: 2022/4/18 20:24
 * Description:
 **/
public interface CodeService {
    PageInfo getAllCodeList(Integer pageNum, Integer pageSize);

    void addCode(AddCode addCode);

    void changStatus(Code code);

    List<CodeVo> searchByName(String userName);

    void deleteCode(Integer id);
}
