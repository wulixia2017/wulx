package com.pagoda.demo.proxy;

import com.pagoda.demo.dto.input.FunctionInput;
import com.pagoda.demo.entity.Function;
import com.pagoda.demo.service.IFunctionService;
import com.pagoda.demo.service.Impl.FunctionServiceImpl;

import java.util.List;

/**
 * 静态代理，与被代理对象共同实现同一个接口，并注入被代理类实例，调用代理方法
 * 缺点：每个被代理对象，都要声明一个代理类增加代理的复杂度。优点：调用放，只知道代理类，不知道被代理类
 * 优点：代理使客户端不需要知道实现类是什么，怎么做的，而客户端只需知道代理即可（解耦合），对于如上的客户端代码，newUserManagerImpl()可以应用工厂将它隐藏，如上只是举个例子而已。
 *
 * 缺点：1）代理类和委托类实现了相同的接口，代理类通过委托类实现了相同的方法。这样就出现了大量的代码重复。如果接口增加一个方法，
 * 除了所有实现类需要实现这个方法外，所有代理类也需要实现此方法。增加了代码维护的复杂度。
 *
 * 2）代理对象只服务于一种类型的对象，如果要服务多类型的对象。势必要为每一种对象都进行代理，静态代理在程序规模稍大时就无法胜任了。
 * 如上的代码是只为UserManager类的访问提供了代理，但是如果还要为其他类如Department类提供代理的话，就需要我们再次添加代理Department的代理类。
 */
public class FunctionProxy implements IFunctionService
{
    private FunctionServiceImpl functionService;

    public FunctionProxy(FunctionServiceImpl functionService) {
        this.functionService = functionService;
    }

    @Override
    public List<Function> findFunctionList(FunctionInput function) {
        System.out.println("static proxy start");
        List<Function> functionList = functionService.findFunctionList(function);
        System.out.println("static proxy end");
        return functionList;
    }
}
