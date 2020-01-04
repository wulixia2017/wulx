package com.pagoda.demo.controller;

import com.alibaba.fastjson.JSON;
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

    @ResponseBody
    @RequestMapping(value = "getMember",method = RequestMethod.GET)
    public ApiResult getMember(@RequestParam int id){
        return ApiResult.success(memberService.getMember(id));
    }
}
