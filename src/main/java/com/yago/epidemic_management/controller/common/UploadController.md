```java
package com.yago.epidemic_management.controller.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: YaGo
 * @Date: 2022/4/9 17:48
 * Description:     文件上传
 **/
@RestController
@ConfigurationProperties(prefix = "file")
public class UploadController {

    public final static String UPLOAD_PATH_PREFIX = "src/main/resources/static";
    public static String uploadPath;

    @Value("${file.uploadPath}")
    public void setFileUploadDir(String fileUploadDir) {
        uploadPath = fileUploadDir;
    }

    /**
     * 获取文件路径
     *
     * @param request  请求体
     * @param fileName 文件名称
     * @return 文件路径
     */
    public String getUploadPath(HttpServletRequest request, String fileName) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + uploadPath + "/" + fileName;
    }

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
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

    // 单文件上传
    @RequestMapping("/file/upload")
    public Object fileUpload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
        Map<String, Object> map = new HashMap();
        map.put("status", 10000);
        map.put("msg", "上传成功");

        if (multipartFile.isEmpty()) {
            map.put("status", 10019);
            map.put("msg", "空文件");
            map.put("data", null);
            return map;
        }

        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        if ("".equals(fileName)) {
            map.put("status", 10020);
            map.put("msg", "文件名不能为空");
            map.put("data", null);
            return map;
        }
        System.out.println("上传文件原始的名字: " + fileName);

        //获取原始名字后缀
        String subName = fileName.substring(fileName.lastIndexOf("."));
        // 使用uuid生成新文件名
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid + subName;
        System.out.println("保存的文件的新名字: " + newFileName);

        // 获取年月日的日期格式
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String format = simpleDateFormat.format(new Date());
        String format = "icons";

        // 生成以日期分割的文件路径
        File readPath = new File(UPLOAD_PATH_PREFIX + uploadPath + File.separator + format);
        System.out.println("存放的文件夹: " + readPath);
        System.out.println("存放文件的绝对路径: " + readPath.getAbsolutePath());
        // 判断文件夹是否存在
        if (!readPath.isDirectory()) {
            // 创建文件夹
            readPath.mkdirs();
        }

        // 文件真实的保存路径
        File file = new File(readPath.getAbsolutePath() + File.separator + newFileName);
        try {
            multipartFile.transferTo(file);

//            // 获取存储路径:http://localhost:8081/uploadFile/icons/df828716-fbd8-42df-94d3-41b7292afeca.jpg
//            String filePath = getUploadPath(request, format + "/" + newFileName);
            String filePath = uploadPath + "/" + newFileName;
            map.put("path", filePath);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("status", 10021);
            map.put("msg", "上传失败");
            map.put("data", null);
        }
        return map;
    }

    // 传统手艺
    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile multipartFile) {
        Map<String, Object> map = new HashMap();
        map.put("msg", "上传成功");

        if (multipartFile.isEmpty()) {
            return "文件不能为空";
        }

        String fileName = multipartFile.getOriginalFilename();
        if ("".equals(fileName)) {
            return "文件名不能为空";
        }
        System.out.println("文件名: " + fileName);

        File readPath = new File(UPLOAD_PATH_PREFIX + uploadPath + File.separator + "temp");
        if (!readPath.isDirectory()) {
            readPath.mkdirs();
        }
        System.out.println("保存路径: " + readPath);

        InputStream is = null;
        FileOutputStream os = null;
        try {
            is = multipartFile.getInputStream();
            os = new FileOutputStream(new File(readPath, fileName));

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = is.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
            map.put("msg", "上传失败");
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return map;
    }

    // 多文件上传
    @PostMapping("/uploads")
    public String uploads(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                return file.getOriginalFilename() + "上传失败";
            }

            File readPath = new File(UPLOAD_PATH_PREFIX + uploadPath + File.separator + "temps");
            System.out.println("多文件上传的位置: " + readPath);
            System.out.println("多文件上传的绝对路径: " + readPath.getAbsolutePath());
            if (!readPath.isDirectory()) {
                readPath.mkdirs();
            }

            File dest = new File(readPath.getAbsolutePath() + File.separator + file.getOriginalFilename());
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
                return file.getOriginalFilename() + "上传失败";
            }
        }
        return "上传成功";
    }
}

```
