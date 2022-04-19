package com.yago.epidemic_management;

import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * @Author: Yago
 * @Date: 2022/3/22 19:31
 * Description:
 **/
public class Test {
    public static void main(String[] args) {


        String uuidString = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        //获取当前时间戳
        long timeMillis = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = dateFormat.format(timeMillis);
        String timeUuid = format + uuidString;
        System.out.println(timeUuid);

    }

}
