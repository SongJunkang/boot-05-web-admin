package com.atguigu.admin.config;


import com.atguigu.admin.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 编写一个拦截器，实现WebMvcConfigurer接口
 * 拦截器注册到容器中，实现WebMvcConfigurer的addInterceptors方法
 * 3.指定拦截器规则，如果是拦截所有，静态资源也会被拦截。
 */
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")//所有请求都会被拦截，包括静态资源。
                .excludePathPatterns("/","/login","/css/**","/fonts/**","/js/**","/images/**", "/templates/error/**","/sql");
    }

}
