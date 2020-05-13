package com.pagoda.demo.aop;

import com.alibaba.fastjson.JSONObject;
import com.pagoda.platform.service.ApiResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一封装api响应
 *
 * @author wulixiaGenerator
 */
@ControllerAdvice("com.pagoda.demo")
public class ApiResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (returnType.hasMethodAnnotation(NoApiResult.class)) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {

        if (body instanceof String || body instanceof JSONObject) {
            return body;
        }
        ApiResult result = null;
        if (body instanceof ApiResult) {
            result = (ApiResult)body;
        } else {
            result = ApiResult.success(body);
        }
        return result;
    }
}
