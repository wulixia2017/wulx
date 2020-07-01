package com.pagoda.demo.controller;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.pagoda.demo.dto.input.FunctionInput;
import com.pagoda.demo.entity.Function;
import com.pagoda.demo.entity.Keywordrecord;
import com.pagoda.demo.entity.TestBean;
import com.pagoda.demo.entity.User;
import com.pagoda.demo.service.IKeywordrecordService;
import com.pagoda.demo.proxy.FunctionProxy;
import com.pagoda.demo.service.Impl.FunctionServiceImpl;
import com.pagoda.demo.utii.ConstantUtil;
import com.pagoda.demo.utii.RedisUtil;
import com.pagoda.platform.service.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("function")
@Api(value = "FunctionController", description = "功能列表管理")
public class FunctionController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private FunctionServiceImpl functionService;

    @Autowired
    private IKeywordrecordService keyWordService;

    @ResponseBody
    @ApiOperation(value = "查询子列表", notes = "关键字查询或者常用列表查询")
    @RequestMapping(value = "findFunctionList",method = RequestMethod.POST)
    public ApiResult findFunctionList(@RequestBody FunctionInput function){
        List<Function> functionList = Lists.newArrayList();
        function.setLevel(ConstantUtil.TWO_LEVEL);
        if (Objects.isNull(function.getTitle())){
            //若是有key，则放入redis，放入最近该用户输入的10条记录
            List<String> keywordList = RedisUtil.getMemberRecentOutList("functionList:"+function.getMemberCode(),function.getTitle(),redisTemplate);
            if (!CollectionUtils.isEmpty(keywordList)){
                //通过联想词查询数据
                String keywordStr = Joiner.on("|").join(keywordList);
                function.setKeyWordRecord(keywordStr);
                functionList = functionService.findFunctionList(function);
            }
        }else{
            //将用户平常用词放入redis
            RedisUtil.getMemberRecentOutList("functionList:"+function.getMemberCode(),function.getTitle(),redisTemplate);
            functionList = functionService.findFunctionList(function);
            if (CollectionUtils.isEmpty(functionList)){
                //根据设置关键词查询
                Keywordrecord keywordrecord = new Keywordrecord();
                keywordrecord.setKeyrecord(function.getTitle());
                List<Keywordrecord> keywordrecordList = this.keyWordService.findKeyWordList(keywordrecord);
                if (!CollectionUtils.isEmpty(keywordrecordList)){
                    List<Integer> keywordIdList = keywordrecordList.stream().map(Keywordrecord::getId).collect(Collectors.toList());
                    function = new FunctionInput();
                    function.setLevel(ConstantUtil.TWO_LEVEL);
                    function.setKeywordList(keywordIdList);
                    functionList = this.functionService.findFunctionList(function);
                }
            }
        }
        return ApiResult.success(functionList);
    }

    @ResponseBody
    @ApiOperation(value = "查询主列表", notes = "查询主列表")
    @RequestMapping(value = "findTopLevelFunctionList",method = RequestMethod.GET)
    public ApiResult findTopLevelFunctionList(){
        FunctionInput functionInput = new FunctionInput();
        functionInput.setLevel(ConstantUtil.ONE_LEVEL);
        return ApiResult.success(functionService.findFunctionList(functionInput));
    }


    @ResponseBody
    @ApiOperation(value = "查询子列表", notes = "查询子列表")
    @RequestMapping(value = "findChildFunctionListByParentId",method = RequestMethod.GET)
    public ApiResult findChildFunctionListByParentId(@RequestParam("parentId") int parentId){
        FunctionInput functionInput = new FunctionInput();
        functionInput.setParentId(parentId);
        return ApiResult.success(functionService.findFunctionList(functionInput));
    }


    @ResponseBody
    @RequestMapping(value = "functionProxy",method = RequestMethod.GET)
    public ApiResult functionProxy(){
        FunctionInput functionInput = new FunctionInput();
        List<Function> functionList = new FunctionProxy(functionService).findFunctionList(functionInput);
        return ApiResult.success(functionList);
    }

}
