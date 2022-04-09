package com.yago.epidemic_management.config;

import com.yago.epidemic_management.interceptor.LoginInterceptor;
import com.yago.epidemic_management.interceptor.RoleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: Yago
 * @Date: 2022/3/22 21:27
 * Description:    配置地址映射和注册拦截器
 **/
@Configuration
@EnableWebMvc
@EnableSwagger2
public class MyMvcConfig implements WebMvcConfigurer {

    /**
     * 拦截器存在于在将请求发送到控制器之前,可能导致自动注入为空
     */
    @Bean
    public RoleInterceptor getRoleInterceptor() {
        return new RoleInterceptor();
    }

    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        /*
         * addResourceHandler:访问映射路径
         * addResourceLocations：资源绝对路径
         */

        //配置swagger的映射路径【http://localhost:8081/swagger-ui.html】
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/templates/")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

    /**
     * 注册拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //定义排除swagger访问的路径配置
        String[] swaggerExcludes = new String[]{"/error", "/static/**", "/doc.html", "/favicon.ico", "/swagger-ui.html", "/swagger-resources/**"};

        registry.addInterceptor(getLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login")  //开放登录路径
                .excludePathPatterns("/adminLogin")     //开放登录路径
                .excludePathPatterns("/admin/**")   //开放角色路径，避免重复校验
                .excludePathPatterns("/register")
                .excludePathPatterns(swaggerExcludes);   //开放注册路径

        registry.addInterceptor(getRoleInterceptor())
                .addPathPatterns("/admin/**")   //拦截admin下的所有路径
                .excludePathPatterns("/static/**");  //开放静态资源

    }
}
//registry.addInterceptor(new UserLoginInterceptor())
        /*
        此时会发现UserLoginInterceptor类中的userService为null，
        并没有因为添加了@Autowird注解而注入进来。
        是因为在WebAppConfigure类中，是new出来的UserLoginInterceptor对象，
        因此不会触发spring的自动注入，导致空指针异常。
        【拦截器加载是在springContext创建之前完成的】
         */
