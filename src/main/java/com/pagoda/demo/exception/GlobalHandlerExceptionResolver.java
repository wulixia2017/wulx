package com.pagoda.demo.exception;

import com.alibaba.fastjson.JSON;
import com.pagoda.demo.utii.ErrorCode;
import com.pagoda.platform.service.ApiResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@Configuration
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println(ex.toString()+ex.getStackTrace());
        if (ex instanceof BusinessException){
            BusinessException businessException = (BusinessException)ex;
            System.out.println(businessException.getMessage()+"   "+businessException.getCode());
        }
        if (ex instanceof Exception){

        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ApiResult apiResult = new ApiResult();
        apiResult.setResultCode(ErrorCode.ERROR_CONVERT_DATE.getCode());
        apiResult.setErrorMsg(ErrorCode.ERROR_CONVERT_DATE.getDescription());
        try {
            writer.write(JSON.toJSONString(apiResult));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView();
    }
}
