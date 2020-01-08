package com.pagoda.demo.annotation;

import java.lang.annotation.*;

//注解策略 SOURCE 源码级别，CLASS 字节码级别，RUNTIME 运行时级别
@Retention(RetentionPolicy.RUNTIME)
//使用目标 (方法，类，接口，实例变量等)
@Target(ElementType.METHOD)
//注解包含在javadoc中
@Documented
//可以被继承
@Inherited
public @interface MyAnnotAge {
    int MaxValue() default 100;
    int minValue() default 0;
}
