package com.salary.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SystemCorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 映射所有请求路径
                .allowedOriginPatterns("*") // 允许所有来源
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的请求方法
                .allowedHeaders("*") // 允许的请求头
                .allowCredentials(true) // 允许发送身份凭证（如Cookie）
                .maxAge(3600); // 预检请求的缓存时间（秒）
    }
}