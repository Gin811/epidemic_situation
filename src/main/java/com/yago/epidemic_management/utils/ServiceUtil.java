package com.yago.epidemic_management.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: YaGo
 * @Date: 2022/4/15 9:17
 * Description:
 **/
public class ServiceUtil {

    //1.获取近七天日期
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

    public static String dateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = simpleDateFormat.format(date);
        return dateString;
    }

}
