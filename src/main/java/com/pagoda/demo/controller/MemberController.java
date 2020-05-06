package com.pagoda.demo.controller;

import com.pagoda.demo.entity.Member;
import com.pagoda.demo.framework.MemberAware;
import com.pagoda.demo.framework.MemberConst;
import com.pagoda.demo.service.IMemberService;
import com.pagoda.platform.service.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
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

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @ResponseBody
    @RequestMapping(value = "getMember",method = RequestMethod.GET)
    public ApiResult getMember(@RequestParam int id){
        return ApiResult.success(memberService.getMember(id));
    }


    @ResponseBody
    @RequestMapping(value = "get",method = RequestMethod.POST)
    public ApiResult get(@RequestBody Member json){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("查询");
        Member member = memberAware.get(json.getId());
        stopWatch.stop();
        System.out.println(member);
        stopWatch.start("查询1111");
        Member member1 = memberConst.get(json.getId());
        stopWatch.stop();
        logger.info("性能：{}",stopWatch.prettyPrint());
        return ApiResult.success(member1);
    }
}
