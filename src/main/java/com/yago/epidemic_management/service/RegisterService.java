package com.yago.epidemic_management.service;

import com.github.pagehelper.PageInfo;
import com.yago.epidemic_management.model.pojo.Register;

/**
 * @Author: Yago
 * @Date: 2022/3/24 18:30
 * Description:
 **/
public interface RegisterService {
    PageInfo getRegisterList(Integer pageNum, Integer pageSize);

    Register selectRegisterUser(Integer id);

    void updateRegisterUser(Register registerUser);

    void addRegisterUser(Register registerDto);

    void deleteRegisterUser(Integer id);

    void batchDeleteRegisterUser(Integer[] ids);
}
