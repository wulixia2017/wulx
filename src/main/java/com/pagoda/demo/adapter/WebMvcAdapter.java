package com.pagoda.demo.adapter;

import com.pagoda.demo.interceptor.TestFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Configuration
public class WebMvcAdapter extends WebMvcConfigurationSupport {
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TestFilter()).addPathPatterns("/**");
    }
}
