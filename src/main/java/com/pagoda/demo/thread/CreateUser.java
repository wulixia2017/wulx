package com.pagoda.demo.thread;

public class CreateUser implements Runnable{
    @Override
    public void run() {
        System.out.println(1111);
    }

    private String name;

    public String getName() {
        return name;
    }

    public CreateUser(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;

    }
}
