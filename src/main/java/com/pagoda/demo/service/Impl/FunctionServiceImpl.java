package com.pagoda.demo.service.Impl;


import com.pagoda.demo.dao.FunctionDao;
import com.pagoda.demo.dao.MemberDao;
import com.pagoda.demo.dto.input.FunctionInput;
import com.pagoda.demo.entity.Function;
import com.pagoda.demo.entity.Member;
import com.pagoda.demo.service.IFunctionService;
import com.pagoda.demo.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionServiceImpl implements IFunctionService{

    @Autowired
    private FunctionDao functionDao;


    public List<Function> findFunctionList(FunctionInput function){
        return functionDao.findFunctionList(function);
    }
}
