package com.yago.epidemic_management.config;

import com.yago.epidemic_management.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Yago
 * @Date: 2022/3/23 11:27
 * Description: User过滤器的配置
 **/
@Configuration
public class UserFilterConfig {

    @Bean
    public UserFilter userFilter() {
        return new UserFilter();
    }

    //将整个filter放到整个链路中去
    @Bean(name = "userFilterConf")//过滤器名字：设置的名字不能和类名一样，不然会有冲突
    public FilterRegistrationBean userFilterConfig() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(userFilter());
        //设置拦截的URL
        filterRegistrationBean.addUrlPatterns("/test");
        //给【过滤器配置】设置名字，以便于区分不同的名字
        filterRegistrationBean.setName("userFilterConf");
        return filterRegistrationBean;
    }
}
