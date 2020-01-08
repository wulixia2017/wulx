package com.pagoda.demo.annotation;


import java.lang.annotation.*;

//注解策略 SOURCE 源码级别（编译阶段丢弃），CLASS 字节码级别（类加载阶段丢弃），RUNTIME 运行时级别(只有这个处理业务时才能找到注解)
@Retention(RetentionPolicy.RUNTIME)
//使用目标 (方法，类，接口，实例变量，参数等)
@Target(ElementType.PARAMETER)
//注解包含在javadoc中
@Documented
//可以被继承
//@Inherited
public @interface CurrentUser {
}
