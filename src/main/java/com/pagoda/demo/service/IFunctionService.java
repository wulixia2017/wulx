package com.pagoda.demo.service;

import com.pagoda.demo.dto.input.FunctionInput;
import com.pagoda.demo.entity.Function;

import java.util.List;

public interface IFunctionService {
    List<Function> findFunctionList(FunctionInput function);

}
