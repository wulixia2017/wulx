package com.pagoda.demo.entity;

/**
 * 单例模式
 */
public class Singleton {

    /**
     * 饿汉式单例，在类初始化时，已经自动实例化
     * 线程安全，即使未使用，也已经初始化，占据一定内存
     */
    private static final Singleton singleton = new Singleton();

    //私有的构造方法
    private Singleton(){}

    //公有的获取实例   静态工厂方法
    public static Singleton getInstance(){
        return singleton;
    }


    /**
     * 懒汉模式，线程不安全，Java反射机制是能够实例化构造方法为private的类的
     * 只有当调用getInstance的时候，才回去初始化这个单例。  所以第一次加载，需要初始化，比较慢
     */
    private static Singleton lazySingleton = null;

    public static  Singleton getLazySingleton(){
        if (lazySingleton == null){
            lazySingleton = new Singleton();
        }
        return lazySingleton;
    }
}
