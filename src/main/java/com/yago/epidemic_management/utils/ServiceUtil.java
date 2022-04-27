package com.yago.epidemic_management.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author: YaGo
 * @Date: 2022/4/15 9:17
 * Description:
 **/
public class ServiceUtil {

    //1.获取近14天日期
    public static List<String> getSevenDate() {
        List<String> dateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 14; i++) {
            Date date = DateUtils.addDays(new Date(), -i);
            String formatDate = sdf.format(date);
            dateList.add(formatDate);
        }
        return dateList;
    }

    //2.日期转为带格式字符串
    public static String dateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);

    }

    //3.生成时间戳UUID
    public static String getTimeUuid() {
        String uuidString = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        //获取当前时间戳
        long timeMillis = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = dateFormat.format(timeMillis);
        return format + uuidString;
    }
}
