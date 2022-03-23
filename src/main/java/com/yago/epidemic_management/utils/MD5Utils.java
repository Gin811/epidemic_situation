package com.yago.epidemic_management.utils;

import com.yago.epidemic_management.common.Constant;
import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5具
 *
 * @Author: Yago
 * @Date: 2022/2/2815:08
 **/
public class MD5Utils {
    //用stasic修饰方便方法的调用
    public static String getMD5Str(String strValue) throws NoSuchAlgorithmException {

        MessageDigest md5 = MessageDigest.getInstance("MD5");   //MD5算法标识
        //为了防解密会加盐值再加密
        return Base64.encodeBase64String(md5.digest((strValue + Constant.SALT).getBytes()));
    }

    //测试
    public static void main(String[] args) {
        String md5Str = null;
        try {
            md5Str = getMD5Str("12345678");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(md5Str);
    }
}
