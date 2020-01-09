package com.pagoda.demo.proxy;
import com.pagoda.demo.service.IFunctionService;
import com.pagoda.demo.service.Impl.CglibSubjectImpl;
import com.pagoda.demo.service.Impl.FunctionServiceImpl;
import com.pagoda.demo.service.Impl.RealSubject;
import com.pagoda.demo.service.Subject;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    /**
     * 定义被代理的对象
     */
    private Object target;

    /**
     * 定义获取代理对象方法
     * @param objectTarget
     * @return
     */
    public Object getCglibProxy(Object objectTarget){
        //为目标对象target赋值
        this.target = objectTarget;
        Enhancer enhancer = new Enhancer();
        //设置父类,因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(objectTarget.getClass());
        //设置回调
        enhancer.setCallback(this);
        //创建并返回代理对象
        Object result = enhancer.create();
        return result;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglibProxy start");
        Object object = method.invoke(target, objects);
        System.out.println("cglibProxy end");
        return object;
    }

    public static void main(String[] args) {
        //实例化CglibProxy对象
        CglibProxy cglib = new CglibProxy();
        CglibSubjectImpl subject =  (CglibSubjectImpl) cglib.getCglibProxy(new CglibSubjectImpl());//获取代理对象
        subject.doSomeThing();
    }
}
