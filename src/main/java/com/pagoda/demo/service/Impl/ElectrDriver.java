package com.pagoda.demo.service.Impl;

import com.pagoda.demo.service.DriverTarget;

public class ElectrDriver implements DriverTarget {
    @Override
    public void driver() {
        System.out.println("电力发动机");
    }
}
