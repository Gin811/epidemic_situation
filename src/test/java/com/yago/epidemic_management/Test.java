package com.yago.epidemic_management;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Yago
 * @Date: 2022/3/22 19:31
 * Description:
 **/
public class Test {
    public static void main(String[] args) {
        List<String> dateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        for (int i = 0; i < 7; i++) {
            Date date = DateUtils.addDays(new Date(), -i);
            String formatDate = sdf.format(date);
            dateList.add(formatDate);
        }
        for (String s : dateList) {
            System.out.println(s);
        }
    }

}
