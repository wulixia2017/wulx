package com.pagoda.demo;

import com.alibaba.fastjson.JSON;
import com.pagoda.demo.dto.input.FunctionInput;
import com.pagoda.demo.entity.Function;
import com.pagoda.demo.entity.Member;
import com.pagoda.demo.entity.TestBean;
import com.pagoda.demo.entity.User;
import com.pagoda.demo.factoryBean.MemberFactoryBean;
import com.pagoda.demo.framework.MemberBean;
import com.pagoda.demo.service.IMemberService;
import com.pagoda.demo.service.Impl.FunctionServiceImpl;
import com.pagoda.demo.service.Impl.MemberServiceImpl;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
public class SimpleWebApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(SimpleWebApplication.class, args);
//        FunctionServiceImpl functionService1 = (FunctionServiceImpl)configurableApplicationContext.getBean("xxoo");
//        List<Function> functionList1 = functionService1.findFunctionList(new FunctionInput());
//        System.out.println(JSON.toJSONString(functionList1));
        ConfigurableListableBeanFactory beanFactory = configurableApplicationContext.getBeanFactory();
//        FunctionServiceImpl functionService = (FunctionServiceImpl)configurableApplicationContext.getBean("functionServiceImpl");
//        List<Function> functionList = functionService.findFunctionList(new FunctionInput());
//        System.out.println(JSON.toJSONString(functionList));

        Member member3 = (Member) beanFactory.getBean("memberFactoryBean");
        member3.setAge("111");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Member member4 = (Member) beanFactory.getBean("memberFactoryBean");
                System.out.println("member4:"+JSON.toJSONString(member4));
                System.out.println(member3==member4);
            }
        }).start();
        Member member4 = (Member) beanFactory.getBean("memberFactoryBean");
        System.out.println(member3==member4);
        System.out.println("memberFactoryBean========="+member3.toString());
        MemberFactoryBean memberFactoryBean = (MemberFactoryBean) beanFactory.getBean(MemberFactoryBean.class);
        MemberServiceImpl memberService1 = (MemberServiceImpl) beanFactory.getBean("memberServiceImpl");
        MemberServiceImpl memberService = beanFactory.getBean(MemberServiceImpl.class);
        memberService.get(1);
        BeanDefinition function = beanFactory.getBeanDefinition("function");
        Function function1 = beanFactory.getBean(Function.class);
        System.out.println(JSON.toJSONString(function1));
        try {
            function1.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Function function2 = beanFactory.getBean(Function.class);
        System.out.println(JSON.toJSONString(function2));
        User user = beanFactory.getBean(User.class);
        User user1 = beanFactory.getBean(User.class);
        System.out.println(user==user1);

        //反射生成实例
        Member member = (Member) Class.forName("com.pagoda.demo.entity.Member").newInstance();
        System.out.println("member===="+member);

    }

}
