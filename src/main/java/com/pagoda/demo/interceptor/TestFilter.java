package com.pagoda.demo.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 拦截器最常用的登录拦截、或是权限校验、或是防重复提交
 * preHandle：在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
 * postHandle：在业务处理器处理请求执行完成后，生成视图之前执行。后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView （这个博主就基本不怎么用了）；
 * afterCompletion：在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）；
 * ————————————————
 *
 * 过滤器和拦截器的区别
 * 最简单明了的区别就是过滤器可以修改request，而拦截器不能
 * 过滤器需要在servlet容器中实现，拦截器可以适用于javaEE，javaSE等各种环境
 * 拦截器可以调用IOC容器中的各种依赖，而过滤器不能
 * 过滤器只能在请求的前后使用，而拦截器可以详细到每个方法
 */
public class TestFilter extends HandlerInterceptorAdapter {

    /**
     * 在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理
     * @param request
     * @param response
     * @param handler
     * @return
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        //判断是否有鉴权注解
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        String methodName = handlerMethod.getMethod().getName();
        System.out.println(methodName);
        System.out.println(request.getMethod());
        System.out.println(request.getHeader("aa"));
        Object object = handlerMethod.getBean();
        JSONObject jsonObject = JSONObject.parseObject(request.getInputStream(), JSONObject.class);
        System.out.println(jsonObject);
        return true;
    }

    /**
     * 在业务处理器处理请求执行完成后，生成视图之前执行。后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        System.out.println(1111);
    }



//    继承HandlerInterceptor的接口，额外提供了afterConcurrentHandlingStarted方法，该方法是用来处理异步请求。当Controller中有异步请求方法的时候会触发该方法。 楼主做过测试，
//    异步请求先支持preHandle、然后执行afterConcurrentHandlingStarted。异步线程完成之后执行preHandle、postHandle、afterCompletion
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response,
                                                Object handler) throws Exception {
    }


}
