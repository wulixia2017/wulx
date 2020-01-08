package com.pagoda.demo.framework;

import com.pagoda.demo.entity.Member;
import com.pagoda.demo.service.Impl.MemberServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MemberConst{

    @Autowired
    private ApplicationContext applicationContext;

    public Member get(int id){
        MemberServiceImpl memberService = (MemberServiceImpl)applicationContext.getBean("memberServiceImpl");
        return memberService.getMember(id);
    }
}
