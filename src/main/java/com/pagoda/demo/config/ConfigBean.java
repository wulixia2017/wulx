package com.pagoda.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.com.cn")
public class ConfigBean {
    private String url;
    private String password;

    public ConfigBean() {
        url = "http";
        password = "123456";
        System.out.println(1111);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
