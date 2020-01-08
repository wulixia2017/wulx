package com.pagoda.demo.thread;

public class SyhTicket {

    //线程同步
//    同步前提：
//            1.必须要有两个或以上的线程
//            2.必须是多个线程使用同一个锁
//            怎么判断哪些代码需要同步:
//                1.哪些代码是多线程运行代码
//            　　2.哪些数据是共享数据
//            　　3.哪些多线程代码是操作共享数据的

    public static void test1(int m){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int n = m;n > 0 ;n--){
                    //处理业务
                    if (n/2 == 0 ){
                        n--;
                        System.out.println(Thread.currentThread().getName()+"===="+n);
                    }else{
                        //若不是偶数，则唤醒另外一个线程
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }).start();
    }

    public static void test2(int m,Boolean flag){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int n = m;n > 0 ;n--){
                    //处理业务
                    if (n/2 == 1 ){
                        n--;
                        System.out.println(Thread.currentThread().getName()+"===="+n);
                    }else{
                        //若不是偶数，则唤醒另外一个线程
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }).start();
    }


}
