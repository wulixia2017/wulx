package com.pagoda.demo.framework;

import org.springframework.beans.factory.BeanNameAware;

public class MemberBean implements BeanNameAware {

    private String beanName;

    @Override
    public void setBeanName(String beanName) {
        if (beanName.equals("functionServiceImpl")){
            beanName = "xxoo";
        }
        this.beanName = beanName;
    }
}
