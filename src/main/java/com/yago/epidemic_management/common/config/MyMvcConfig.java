package com.yago.epidemic_management.common.config;

import com.yago.epidemic_management.interceptor.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: Yago
 * @Date: 2022/3/22 21:27
 * Description:    配置地址映射
 **/
@Configuration
@EnableWebMvc
@EnableSwagger2
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        /*
         * addResourceHandler:访问映射路径
         * addResourceLocations：资源绝对路径
         */

        //配置swagger的映射路径
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginInterceptor())
                .addPathPatterns("/admin/**")//拦截admin下的所有路径
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/login")//开放登录路径
                .excludePathPatterns("/adminLogin");//开放登录路径
    }
}
