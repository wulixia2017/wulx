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

    /**
     * 这个就是我们要代理的真实对象
     */
    private Object target;

    /**
     * 构造方法，给我们要代理的真实对象赋初值
     * @param target
     */
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

    /**
     * 该方法负责集中处理动态代理类上的所有方法调用
     * 调用处理器根据这三个参数进行预处理或分派到委托类实例上反射执行
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("auto proxy start1111");
        Object object = method.invoke(target, args);
        System.out.println("auto proxy end1111");
        return object;
    }

    public static void main(String[] args)throws Exception {
        //代理的真实对象
        Subject realSubject = new RealSubject();

        /**
         * InvocationHandlerImpl 实现了 InvocationHandler 接口，并能实现方法调用从代理类到委托类的分派转发
         * 其内部通常包含指向委托类实例的引用，用于真正执行分派转发过来的方法调用.
         * 即：要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法
         */
        InvocationHandler handler = new JdkFunctionAutoProxy(realSubject);
        ClassLoader loader = realSubject.getClass().getClassLoader();
        Class[] interfaces = realSubject.getClass().getInterfaces();
        /**
         * 该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例
         */
        Subject subject = (Subject) Proxy.newProxyInstance(loader, interfaces, handler);

        System.out.println("动态代理对象的类型："+subject.getClass().getName());

        subject.doSomeThing();
    }
}
