package com.yago.epidemic_management.config;

import com.yago.epidemic_management.filter.AdminFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Yago
 * @Date: 2022/3/23 11:36
 * Description:
 **/
@Configuration
public class AdminFilterConfig {

    @Bean
    public AdminFilter adminFilter() {
        return new AdminFilter();
    }

    //将整个filter放到整个链路中去
    @Bean(name = "adminFilterConf")//过滤器名字：设置的名字不能和类名一样，不然会有冲突
    public FilterRegistrationBean adminFilterConfig() {
        FilterRegistrationBean filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(adminFilter());
        //设置拦截路劲
        filterFilterRegistrationBean.addUrlPatterns("/admin/*");
//        filterFilterRegistrationBean.addUrlPatterns("/admin/update");
//        filterFilterRegistrationBean.addUrlPatterns("/admin/delete");
//        filterFilterRegistrationBean.addUrlPatterns("/admin/egresslist");
        //给【过滤器配置】设置名字，以便于区分不同的名字
        filterFilterRegistrationBean.setName("adminFilterConf");
        return filterFilterRegistrationBean;
    }

}
