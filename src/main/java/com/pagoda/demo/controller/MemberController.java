package com.pagoda.demo.controller;

import com.pagoda.demo.entity.Member;
import com.pagoda.demo.framework.MemberAware;
import com.pagoda.demo.framework.MemberConst;
import com.pagoda.demo.service.IMemberService;
import com.pagoda.platform.service.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("member")
public class MemberController {

    @Autowired
    private IMemberService memberService;

    @Autowired
    private MemberAware memberAware;

    @Autowired
    private MemberConst memberConst;

    @ResponseBody
    @RequestMapping(value = "getMember",method = RequestMethod.GET)
    public ApiResult getMember(@RequestParam int id){
        return ApiResult.success(memberService.getMember(id));
    }


    @ResponseBody
    @RequestMapping(value = "get",method = RequestMethod.GET)
    public ApiResult get(@RequestParam int id){
        Member member = memberAware.get(id);
        System.out.println(member);
        Member member1 = memberConst.get(id);
        System.out.println(member1);
        return ApiResult.success(member1);
    }
}
