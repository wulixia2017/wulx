package com.pagoda.demo.entity;


public class OtherSington {
    //懒汉模式，需要的时候在创建
    private static OtherSington sington = null;

    //不允许外界调用
    private OtherSington() {

    }

    //线程不安全，若是并发判断实例为空，则会多创建实例，需要加同步关键字修饰
    public static synchronized OtherSington getSington(){
        if (sington == null){
            sington = new OtherSington();
        }
        return sington;
    }


    //饿汉模式，初始化时，就已经创建
    private static final OtherSington ehanSington = new OtherSington();

    public static OtherSington getEhanSington(){
        return ehanSington;
    }

}
