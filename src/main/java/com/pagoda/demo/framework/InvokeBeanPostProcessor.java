package com.pagoda.demo.framework;

import com.pagoda.demo.entity.Function;
import com.pagoda.demo.entity.TestBean;
import com.pagoda.demo.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class InvokeBeanPostProcessor implements BeanPostProcessor {
    @Nullable
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Function){
            ((Function) bean).setId(44444);
            System.out.println("function,before===================");
        }
        if (bean instanceof TestBean){
            System.out.println("TestBean,before===================");
        }
        if (bean instanceof User){
            System.out.println("User,before===================");
        }
        return bean;
    }

    @Nullable
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Function){
            ((Function) bean).setId(55555);
            System.out.println("function,after===================");
        }
        if (bean instanceof TestBean){
            System.out.println("TestBean,after===================");
        }
        if (bean instanceof User){
            System.out.println("User,after===================");
        }
        return bean;
    }
}
