package com.pagoda.demo.annotation;

import java.lang.annotation.*;

/**
 * 注解相当于一种注释，描述对该字段，该方法的处理逻辑，也是一个标记，对于实现该注解的方法，类，接口等实现业务处理
 */

//注解策略 SOURCE 源码级别（编译阶段丢弃），CLASS 字节码级别（类加载阶段丢弃），RUNTIME 运行时级别(只有这个处理业务时才能找到注解)
@Retention(RetentionPolicy.RUNTIME)
//使用目标 (方法，类，接口，实例变量等)
@Target(ElementType.METHOD)
//注解包含在javadoc中
@Documented
//可以被继承
@Inherited
public @interface MyAnnot {
    String value() default "邬礼夏";
}
