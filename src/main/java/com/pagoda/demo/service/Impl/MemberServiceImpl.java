package com.pagoda.demo.service.Impl;


import com.pagoda.demo.annotation.MyAnnot;
import com.pagoda.demo.dao.MemberDao;
import com.pagoda.demo.entity.Member;
import com.pagoda.demo.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private MemberDao memberDao;

    @MyAnnot
    public Member getMember(int id){
        Member member = memberDao.getMember(id);
        return member;
    }
}
