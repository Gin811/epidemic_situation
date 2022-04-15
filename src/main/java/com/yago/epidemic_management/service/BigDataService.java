package com.yago.epidemic_management.service;

import com.yago.epidemic_management.model.dos.SevenDayDo;

import java.util.List;
import java.util.Map;

/**
 * @Author: YaGo
 * @Date: 2022/4/15 13:02
 * Description:
 **/
public interface BigDataService {

    List<SevenDayDo> selectByDayLeaveAll();

    List<SevenDayDo> selectByDayReturnAll();

    List<Map> selectCountHouse();
}
