package com.pagoda.demo.framework;

import com.pagoda.demo.entity.Member;
import com.pagoda.demo.service.Impl.MemberServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MemberAware implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Member get(int id){
        MemberServiceImpl memberService = (MemberServiceImpl)applicationContext.getBean("memberServiceImpl");
        return memberService.getMember(id);
    }
}
