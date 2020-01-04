package com.pagoda.demo.dao;

import com.pagoda.demo.dto.input.FunctionInput;
import com.pagoda.demo.entity.Function;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FunctionDao {
    List<Function> findFunctionList(FunctionInput FunctionInput);
}
