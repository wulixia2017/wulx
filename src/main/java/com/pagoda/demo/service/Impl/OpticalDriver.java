package com.pagoda.demo.service.Impl;

import com.pagoda.demo.service.DriverTarget;

public class OpticalDriver implements DriverTarget {
    @Override
    public void driver() {
        System.out.println("光能发动机");
    }
}
