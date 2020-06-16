package com.pagoda.demo.service.Impl;


import com.pagoda.demo.annotation.MyAnnot;
import com.pagoda.demo.dao.MemberDao;
import com.pagoda.demo.entity.Member;
import com.pagoda.demo.exception.BusinessException;
import com.pagoda.demo.service.IMemberService;
import com.pagoda.demo.utii.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private MemberDao memberDao;

    @Transactional
    @MyAnnot
    public Member getMember(int id){
        Member member = memberDao.getMember(id);
//        if (member == null){
//            throw new BusinessException(ErrorCode.ERROR_CONVERT_DATE);
//        }
        return member;
    }
}
