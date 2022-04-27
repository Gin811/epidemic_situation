package com.yago.epidemic_management.service.Impl;

import com.yago.epidemic_management.model.dao.BigDataMapper;
import com.yago.epidemic_management.model.dos.SevenDayDo;
import com.yago.epidemic_management.service.BigDataService;
import com.yago.epidemic_management.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: YaGo
 * @Date: 2022/4/15 13:02
 * Description:
 **/
@Service
public class BigDataServiceImpl implements BigDataService {
    @Autowired
    BigDataMapper bigDataMapper;

    /**
     * 查询每日离开人数
     *
     * @return
     */
    @Override
    public List<SevenDayDo> selectByDayLeaveAll() {
        try {
            List<String> sevenDate = ServiceUtil.getSevenDate();
            List<SevenDayDo> oldList = bigDataMapper.selectByDayLeaveAll(sevenDate);
            List<SevenDayDo> newList = new ArrayList<>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            //外层循环
            for (int i = 0; i < sevenDate.size(); i++) {
                SevenDayDo sevenDayDo = new SevenDayDo();
                String dateString = sevenDate.get(i);
                Date date = simpleDateFormat.parse(dateString);
                sevenDayDo.setDate(date);
                boolean bool = false;
                for (int m = 0; m < oldList.size(); m++) {
                    String s = ServiceUtil.dateToString(oldList.get(m).getDate());
                    if (sevenDate.get(i).equals(s)) {
                        sevenDayDo.setCount(oldList.get(m).getCount());
                        bool = true;
                        break;
                    }
                }
                if (!bool) {
                    sevenDayDo.setCount(0);
                }
                newList.add(sevenDayDo);
            }
            Collections.reverse(newList);
            return newList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询每日返回人数
     *
     * @return
     */
    @Override
    public List<SevenDayDo> selectByDayReturnAll() {
        try {
            //获取近七天日期
            List<String> sevenDate = ServiceUtil.getSevenDate();
            //查询近七天完成任务数量方法
            List<SevenDayDo> oldList = bigDataMapper.selectByDayReturnAll(sevenDate);
            //创建一个arraylist对象
            List<SevenDayDo> newList = new ArrayList<>();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            //外层七天循环  循环七次
            for (int i = 0; i < sevenDate.size(); i++) {
                //创建封装类对象  循环七次 创建七个
                SevenDayDo sevenDayDo = new SevenDayDo();
                //将近七天日期添加进封装类中
                String dateString = sevenDate.get(i);
                Date date = ft.parse(dateString);
                sevenDayDo.setDate(date);
                boolean bool = false;
                //创建内循环 根据查询出已有的数量 循环次数
                for (int m = 0; m < oldList.size(); m++) {
                    String s = ServiceUtil.dateToString(oldList.get(m).getDate());
                    if (sevenDate.get(i).equals(s)) {
                        sevenDayDo.setCount(oldList.get(m).getCount());
                        bool = true;
                        break;
                    }
                }
                if (!bool) {
                    sevenDayDo.setCount(0);
                }
                newList.add(sevenDayDo);
            }
            Collections.reverse(newList);
            return newList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询每栋住户数量
     *
     * @return
     */
    @Override
    public List<Map> selectCountHouse() {
        String sql = "SELECT left(address,1) Num,count(*) Sum FROM epidemic_situation_user GROUP BY left(address,1) ORDER BY Num";
        List<Map> maps = bigDataMapper.selectCountHouse(sql);
        return maps;
    }

    /**
     * 按类型统计隔离人数
     *
     * @return
     */
    @Override
    public Map selectDivide() {
        Map map = bigDataMapper.selectDivide();
        return map;
    }
}
