package com.starvey.config;

import com.starvey.interceptor.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Yixiang Zhao (@seriouszyx)
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new UserLoginInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns(
                "/toLogin",
                "/login",
                "/callback",
                "/**/*.html",
                "/**/*.js",
                "/**/*.css",
                "/swagger**/**",    // 开放 swagger 相关接口
                "/webjars/**",
                "/v3/**",
                "/doc.html",
                "/test"             // 开放测试接口
        );
    }
}
