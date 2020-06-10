package com.pagoda.demo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Badvice{

    @Pointcut("@annotation(com.pagoda.demo.annotation.MyAnnot)")
    public void pointcut(){}

    @Before("pointcut()")
    public void before() {
        System.out.println("before");
    }
}
