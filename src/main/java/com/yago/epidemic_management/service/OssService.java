package com.yago.epidemic_management.service;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssService {
    public String uploadFile(MultipartFile file) {
        //1.配置OOS相关配置文件
        String endpoint = "oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "LTAI5tQWenYEaznq97X2mGJE";
        String accessKeySecret = "I3LUtUXZDItZ3Ise0fOXOI8TnhxSqm";
        String bucketName = "yago";

        //2.得到图片原始名字
        String fn = file.getOriginalFilename();

        //3.生成新的文件名
        String fileName = UUID.randomUUID() + fn.substring(fn.indexOf("."));
        //4.创建文件夹【epidemic-management/fileName】
        String objectName = "epidemic-management/" + fileName;

        //5.创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build("https://" + endpoint, accessKeyId, accessKeySecret);

        try {
            InputStream inputStream = file.getInputStream();
            System.out.println(objectName);
            ossClient.putObject(bucketName, objectName, inputStream);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        //https://yago.oss-cn-hangzhou.aliyuncs.com/epidemic-management/9695ac3c-dc58-4697-87d0-7af50d5dd4ef.jpg
        return "https://" + bucketName + "." + endpoint + "/" + objectName;
    }

    public String uploadIcon(MultipartFile file) {
        //1.配置OOS相关配置文件
        String endpoint = "oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "LTAI5tQWenYEaznq97X2mGJE";
        String accessKeySecret = "I3LUtUXZDItZ3Ise0fOXOI8TnhxSqm";
        String bucketName = "yago";

        //2.得到图片原始名字
        String fn = file.getOriginalFilename();

        //3.生成新的文件名
        String fileName = UUID.randomUUID() + fn.substring(fn.indexOf("."));
        //4.创建文件夹【epidemic-management/fileName】
        String objectName = "epidemic-management/icons/" + fileName;

        //5.创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build("https://" + endpoint, accessKeyId, accessKeySecret);

        try {
            InputStream inputStream = file.getInputStream();
            System.out.println(objectName);
            ossClient.putObject(bucketName, objectName, inputStream);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        //https://yago.oss-cn-hangzhou.aliyuncs.com/epidemic-management/icons/9695ac3c-dc58-4697-87d0-7af50d5dd4ef.jpg
        return "https://" + bucketName + "." + endpoint + "/" + objectName;
    }


}
