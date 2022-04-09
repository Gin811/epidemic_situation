package com.yago.epidemic_management.controller.common;

import io.swagger.annotations.ApiOperation;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.yago.epidemic_management.common.Constant.UPLOAD_PATH_PREFIX;
import static com.yago.epidemic_management.common.Constant.uploadPath;

/**
 * @Author: YaGo
 * @Date: 2022/4/9 23:11
 * Description:     打印项目相关信息
 **/
@RestController
public class TestController {
    @ApiOperation("打印上传数据相关信息")
    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {


        /**
         * 获取文件路径
         *
         * @param request  请求体
         * @param fileName 文件名称
         * @return 文件路径
         */


        System.out.println("uploadPath --> " + uploadPath);
        System.out.println("上下文2 --> " + request.getServletContext().getRealPath("/file"));

        File file = new File(UPLOAD_PATH_PREFIX);
        System.out.println("UPLOAD_PATH_PREFIX 路径: " + file.getAbsoluteFile());

        try {
            String path = ResourceUtils.getURL("classpath:").getPath();
            System.out.println("ResourceUtils.getURL.getPath --> " + path);

            System.out.println("当前项目的绝对路径 --> " + new File("").getAbsolutePath());
            System.out.println("upload 绝对路径 --> " + new File("upload").getAbsolutePath());
            System.out.println("/upload 绝对路径 --> " + new File("/upload").getAbsolutePath());
            System.out.println("./upload 绝对路径 --> " + new File("./upload").getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        return format;
    }
}
