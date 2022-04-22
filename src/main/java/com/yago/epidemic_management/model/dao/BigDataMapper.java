package com.yago.epidemic_management.model.dao;

import com.yago.epidemic_management.model.dos.SevenDayDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: YaGo
 * @Date: 2022/4/15 13:01
 * Description:
 **/
@Mapper
public interface BigDataMapper {
    List<SevenDayDo> selectByDayLeaveAll(List<String> sevenDate);

    List<SevenDayDo> selectByDayReturnAll(List<String> sevenDate);

    List<Map> selectCountHouse(@Param("sql") String sql);

    Map selectDivide();

}
