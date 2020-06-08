package com.pagoda.demo.filter;


import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Map;

//写filter的实现类，主要就是又加了@Configuration 初始化容器 这个注解，会根据WebFilter生成Bean。
@Configuration
@WebFilter(urlPatterns = "/function/*", filterName = "member")
public class DoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain dochain) throws IOException, ServletException {
        System.out.println(request.getParameter("id"));
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(JSON.toJSONString(parameterMap));
        System.out.println(request.getParameter("id"));
        dochain.doFilter(request,response);
    }




    @Override
    public void destroy() {

    }
}
