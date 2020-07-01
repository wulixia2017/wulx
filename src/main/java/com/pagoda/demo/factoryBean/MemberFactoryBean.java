package com.pagoda.demo.factoryBean;

import com.pagoda.demo.entity.Member;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MemberFactoryBean implements FactoryBean<Member> {
    @Override
    public Member getObject() throws Exception {
        Member member = new Member();
        return member;
    }

    @Override
    public Class<Member> getObjectType() {
        return Member.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
