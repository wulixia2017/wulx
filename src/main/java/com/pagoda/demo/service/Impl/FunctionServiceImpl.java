package com.pagoda.demo.service.Impl;


import com.pagoda.demo.dao.FunctionDao;
import com.pagoda.demo.dto.input.FunctionInput;
import com.pagoda.demo.entity.Function;
import com.pagoda.demo.framework.TranscationSync;
import com.pagoda.demo.service.IFunctionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FunctionServiceImpl implements IFunctionService{

    @Resource
    private FunctionDao functionDao;

    @Transactional
    public List<Function> findFunctionList(FunctionInput function){
        System.out.println("begin transcation");
        List<Function> functionList = functionDao.findFunctionList(function);
        System.out.println("end transcation");
        TranscationSync transcationSync = new TranscationSync();
        TransactionSynchronizationManager.registerSynchronization(transcationSync);
        return functionList;
    }
}
