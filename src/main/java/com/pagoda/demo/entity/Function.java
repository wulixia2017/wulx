package com.pagoda.demo.entity;

import com.pagoda.platform.dto.BaseEntity;
import io.swagger.annotations.Scope;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope(name = "prototype",description = "prototype")
public class Function implements BaseEntity,ApplicationContextAware,InitializingBean,DisposableBean {
    private int id;
    private String title;
    private String content;
    private String type;
    private String tip;
    private String action;
    private Integer keyword;
    private String functionType;
    private Integer level;

    public Function() {
        System.out.println("construct==========================");
    }

    private ApplicationContext applicationContext;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getFunctionType() {
        return functionType;
    }

    public void setFunctionType(String functionType) {
        this.functionType = functionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getKeyword() {
        return keyword;
    }

    public void setKeyword(Integer keyword) {
        this.keyword = keyword;
    }

    @Override
    public void setApplicationContext(ApplicationContext arg) throws BeansException {
        applicationContext = arg;
        System.out.println("aware=======================");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet=======================");
        setId(1);
        setTitle("李四");
    }

    @PostConstruct
    public void initMethod() {
        System.out.println("initMethod=======================");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean====================");
    }
}
