package com.pagoda.demo.thread;

import com.google.common.collect.Lists;

import java.util.List;

public class DoProject{

    //多线程，只需要重写runable中的run方法
    public static void getU(List<String> list){
        int size = list.size();
        List<String> result = Lists.newArrayList();
        while (size > 1){
            List<String> list1 = Lists.newArrayList();
            list1.add(list.get(size-1));
            list1.add(list.get(size-2));
            String str = runThread(list1);
            result.add(str);
            size = size-2;
        }
        System.out.println("++++++");
        result.forEach(n ->{
            System.out.println("===="+n);
        });
    }

    public static String runThread(List<String> list){
        //thread 的匿名类，重写run方法处理业务，list传参
        StringBuilder sb = new StringBuilder("");
        new Thread(){
            public void run() {
                for (String str : list){
                    sb.append(str).append(",");
                }
                for (int i = 0;i < 10;i++){
                    if (sb.toString().contains("EEEEEEEE")){
                        try {
                            this.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(this.getName()+"+++"+sb.toString()+"+++"+i);
                }
            }
        }.start();
        return sb.toString();
    }

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList();
        list.add("0000");
        list.add("1111");
        list.add("2222");
        list.add("3333");
        list.add("44444");
        list.add("BBBBBBBB");
        list.add("CCCCCCCC");
        list.add("DDDDDDDD");
        list.add("EEEEEEEE");
        list.add("RRRRRRRR");
        getU(list);
    }
}
