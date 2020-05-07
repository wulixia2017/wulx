package com.pagoda.demo.adapter;

import com.pagoda.demo.service.DriverTarget;

public class DriverAdapt {
    static DriverTarget getObject(String name){
        try {
            return (DriverTarget)Class.forName(name).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        DriverTarget driverTarget = getObject("com.pagoda.demo.service.Impl.ElectrDriver");
        driverTarget.driver();
    }
}
