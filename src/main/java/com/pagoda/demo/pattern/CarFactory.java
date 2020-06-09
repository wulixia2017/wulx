package com.pagoda.demo.pattern;

/**
 * 工厂模式，由工厂去生成实例对象
 */
public class CarFactory {

    public CarPatton getCar(String car){
        if (car.equals("bigCar")){
            return new BiglCar();
        }else if(car.equals("smallCar")){
            return new SmallCar();
        }
        return null;
    }
}
