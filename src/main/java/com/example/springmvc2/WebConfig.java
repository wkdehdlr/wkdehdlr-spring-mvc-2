package com.example.springmvc2;

import com.example.springmvc2.web.filter.LogFilter;
import com.example.springmvc2.web.filter.LoginCheckerFilter;
import javax.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<Filter> logFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LogFilter());
        filterFilterRegistrationBean.setOrder(1);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        return filterFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> loginCheckFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LoginCheckerFilter());
        filterFilterRegistrationBean.setOrder(2);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        return filterFilterRegistrationBean;
    }
}
