package com.pagoda.demo.Dispatch;

import com.pagoda.demo.exception.BusinessException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class DoException extends DispatcherServlet {
    @Override
    protected ModelAndView processHandlerException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex instanceof BusinessException){
            System.out.println("businessException");
        }
        ModelAndView view = new ModelAndView();
        view.setStatus(HttpStatus.EXPECTATION_FAILED);
        view.setViewName("异常提示");
        return view;
    }
}
