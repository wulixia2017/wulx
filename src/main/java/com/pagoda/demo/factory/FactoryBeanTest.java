package com.pagoda.demo.factory;

import com.pagoda.demo.entity.User;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class FactoryBeanTest implements FactoryBean<User> {


    @Override
    public User getObject() throws Exception {
        System.out.println("factoryBeanTest");
        return new User();
    }

    @Override
    public Class<User> getObjectType() {
        return User.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
