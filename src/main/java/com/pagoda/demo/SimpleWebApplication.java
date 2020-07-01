package com.pagoda.demo;

import com.alibaba.fastjson.JSON;
import com.pagoda.demo.config.ConfigBean;
import com.pagoda.demo.dto.input.FunctionInput;
import com.pagoda.demo.entity.Function;
import com.pagoda.demo.entity.Member;
import com.pagoda.demo.entity.User;
import com.pagoda.demo.factory.FactoryBeanTest;
import com.pagoda.demo.framework.MemberBean;
import com.pagoda.demo.service.IMemberService;
import com.pagoda.demo.service.Impl.FunctionServiceImpl;
import com.pagoda.demo.service.Impl.MemberServiceImpl;
import com.pagoda.demo.thread.LingYiThread;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * @author PagodaGenerator
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties
public class SimpleWebApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(SimpleWebApplication.class, args);

//        FunctionServiceImpl functionService1 = (FunctionServiceImpl)configurableApplicationContext.getBean("xxoo");
//        List<Function> functionList1 = functionService1.findFunctionList(new FunctionInput());
//        System.out.println(JSON.toJSONString(functionList1));
//        ConfigurableListableBeanFactory beanFactory = configurableApplicationContext.getBeanFactory();
//        FunctionServiceImpl functionService = (FunctionServiceImpl)configurableApplicationContext.getBean("functionServiceImpl");
//        List<Function> functionList = functionService.findFunctionList(new FunctionInput());
//        System.out.println(JSON.toJSONString(functionList));
//        LingYiThread lingYiThread = (LingYiThread)configurableApplicationContext.getBeanFactory().getBean("lingYiThread");
//        lingYiThread.setCustomerID(1111);
//        lingYiThread.run();
//        new Thread(lingYiThread).start();
//        System.out.println(Thread.currentThread().getName());
//        final ConfigBean bean = configurableApplicationContext.getBeanFactory().getBean(ConfigBean.class);
//        MemberServiceImpl memberService = configurableApplicationContext.getBeanFactory().getBean(MemberServiceImpl.class);
//        FactoryBeanTest factoryBeanTest = configurableApplicationContext.getBean(FactoryBeanTest.class);
//        User user = factoryBeanTest.getObject();
//        System.out.println(factoryBeanTest.getObject() == user);
//        System.out.println(user);
//        System.out.println(factoryBeanTest.getObject());
    }



}
