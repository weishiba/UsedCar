package com.wsc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wsc
 * @date 2021/4/27
 */
@Configuration
public class permissionConfig implements WebMvcConfigurer {
    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        InterceptorRegistration registration = registry.addInterceptor(new PermissionInterceptor());
        //添加拦截路径
        registration.addPathPatterns("/**");
        //剔除拦截路径
        registration.excludePathPatterns("/static/**");
        registration.excludePathPatterns("/mapper/**");
        registration.excludePathPatterns("/templates/user/index.html");
        registration.excludePathPatterns("/user/**");
    }*/
}
