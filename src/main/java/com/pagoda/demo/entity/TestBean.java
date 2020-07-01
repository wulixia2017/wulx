package com.pagoda.demo.entity;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TestBean{

    @Bean
    @Scope("singleton")
    public User getUser(){
        System.out.println("getUser=========================");
        User user = new User();
        user.setAge(1111111);
        return user;
    }
}
