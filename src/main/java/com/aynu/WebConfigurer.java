package com.aynu;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/12 17:07
 * @description
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(new LoginInterceptor());
        addInterceptor.excludePathPatterns("/static/**");
        addInterceptor.addPathPatterns("/jump/**");
        addInterceptor.addPathPatterns("/teacher/**");
        addInterceptor.addPathPatterns("/super/**");
        addInterceptor.addPathPatterns("/file/**");
        addInterceptor.addPathPatterns("/department/**");
        addInterceptor.addPathPatterns("/school/**");
        addInterceptor.addPathPatterns("/tjump/**");
        addInterceptor.addPathPatterns("/ttjump/**");
        addInterceptor.addPathPatterns("/tttjump/**");
        addInterceptor.addPathPatterns("/ttttjump/**");
        addInterceptor.addPathPatterns("/djump/**");
        addInterceptor.addPathPatterns("/ddjump/**");
        addInterceptor.addPathPatterns("/dddjump/**");
        addInterceptor.addPathPatterns("/ddddjump/**");
        addInterceptor.addPathPatterns("/sjump/**");
        addInterceptor.addPathPatterns("/ssjump/**");
        addInterceptor.addPathPatterns("/sssjump/**");
        addInterceptor.addPathPatterns("/ssssjump/**");
        addInterceptor.addPathPatterns("/zscq/**");
        addInterceptor.addPathPatterns("/mxhzb/**");
        addInterceptor.addPathPatterns("/tjb/**");
        addInterceptor.addPathPatterns("/tjt/**");
        addInterceptor.addPathPatterns("/kytd/**");
        addInterceptor.addPathPatterns("/ptgl/**");
        //没有加 school 的拦截器 也没有提示 问题
    }


}
