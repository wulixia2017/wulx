package com.pagoda.demo.proxy;

import com.pagoda.demo.service.Impl.RealSubject;
import com.pagoda.demo.service.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * java动态代理是利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理。
 * 而cglib动态代理是利用asm开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。
 * 1、如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP
 * 2、如果目标对象实现了接口，可以强制使用CGLIB实现AOP
 * 3、如果目标对象没有实现了接口，必须采用CGLIB库，spring会自动在JDK动态代理和CGLIB之间转换
 *
 * JDK动态代理只能对实现了接口的类生成代理，而不能针对类
 * CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法
 */
public class JdkFunctionAutoProxy implements InvocationHandler {

    private Object target;

    public JdkFunctionAutoProxy(Object target) {
        this.target = target;
    }

    /**
     * 获取被代理接口实例对象
     * @param <T>
     * @return
     */
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("auto proxy start");
        Object object = method.invoke(target, args);
        System.out.println("auto proxy end");
        return object;
    }

    public static void main(String[] args)throws Exception {
        // jdk动态代理测试
        RealSubject subject = new JdkFunctionAutoProxy(new RealSubject()).getProxy();
        subject.doSomeThing();
    }
}
