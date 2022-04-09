package com.yago.epidemic_management.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Yago
 * @Date: 2022/3/22 16:12
 * Description:常量值
 **/
@Component
@ConfigurationProperties(prefix = "file")
public class Constant {
    public static final String MPIDEMICMANAGEMENT_USER = "epidemicmanagement_user";

    //加密用的盐值
    public static final String SALT = "dfafaSFf&&01F^%";

    //图片上传路径
    public final static String UPLOAD_PATH_PREFIX = "src/main/resources/static";
    public static String uploadPath;

    @Value("${file.uploadPath}")
    public void setFileUploadDir(String fileUploadDir) {
        uploadPath = fileUploadDir;
    }
}
