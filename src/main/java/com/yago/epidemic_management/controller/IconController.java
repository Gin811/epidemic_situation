package com.yago.epidemic_management.controller;

import com.yago.epidemic_management.model.dto.add.AddIconDto;
import com.yago.epidemic_management.model.pojo.Icon;
import com.yago.epidemic_management.service.IconService;
import com.yago.epidemic_management.service.OssService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.yago.epidemic_management.common.Constant.UPLOAD_PATH_PREFIX;
import static com.yago.epidemic_management.common.Constant.uploadPath;

/**
 * @Author: YaGo
 * @Date: 2022/4/9 22:43
 * Description:
 **/
@RestController
@RequestMapping("/icon")
public class IconController {

    @Autowired
    IconService iconService;
    @Autowired
    OssService ossService;

    // OSS单文件上传
    @ApiOperation("OOS单文件上传")
    @RequestMapping(value = ("/oosFile/upload"), headers = ("content-type=multipart/*"), method = RequestMethod.POST)
    public Object OOSFileUpload(@RequestParam("file") MultipartFile multipartFile) {
        String path = ossService.uploadIcon(multipartFile);
        Map<String, Object> map = new HashMap();
        map.put("status", 200);
        map.put("msg", "成功");
        map.put("data", path);
        return map;
    }

    //本机上传图片
    @ApiOperation("本机单文件上传")
    @RequestMapping(value = ("/file/upload"), headers = ("content-type=multipart/*"), method = RequestMethod.POST)
    public Object fileUpload(@RequestParam("file") MultipartFile multipartFile) {

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

        // 保存在icons文件夹下
        String format = "icons";

        // 生成文件路径
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
            // 获取存储路径:http://localhost:8081/uploadFile/icons/df828716-fbd8-42df-94d3-41b7292afeca.jpg

            String filePath = uploadPath + "/" + format + "/" + newFileName;
            map.put("path", filePath);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("status", 10021);
            map.put("msg", "上传失败");
            map.put("data", null);
        }
        return map;
    }

    @ApiOperation("上传路径到数据库")
    @RequestMapping("/add")
    public Map addIcon(@Validated @RequestBody AddIconDto addIconDto) {
        iconService.addIcon(addIconDto);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 10000);
        map.put("msg", "添加成功");
        map.put("data", null);

        return map;
    }

    @GetMapping("/queryIcon")
    public Map<String, Object> queryIcon(@RequestParam String userId) {
        Icon icon = iconService.queryIcon(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 10000);
        map.put("msg", "请求成功");
        map.put("data", icon);
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
