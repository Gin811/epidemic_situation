package com.yago.epidemic_management.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Yago
 * @Date: 2022/3/26 20:32
 * Description: 解决请求跨域问题
 **/
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路由
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                //放行哪些请求方式
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                //.allowedMethods("*") 【或者放行全部】
                //是否允许证书（cookies）【发送Cookie】
                .allowCredentials(true)
                //允许跨域的时间
                .maxAge(3600)
                //放行哪些原始请求头部信息
                .allowedHeaders("*");
    }
}
