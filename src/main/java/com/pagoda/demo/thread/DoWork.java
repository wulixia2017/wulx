package com.pagoda.demo.thread;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DoWork {
//        ThreadPoolExecutor
        public static void createUser(List<String> list){
            ExecutorService threadpool = Executors.newScheduledThreadPool(3);
            List<String> list1 = list.subList(0,2);
            String str1 = createPool(list1,threadpool);
            System.out.println("str1:"+str1);
            List<String> list2 = list.subList(2,4);
            String str2 = createPool(list2,threadpool);
            System.out.println("str2:"+str2);
            List<String> list3 = list.subList(4,6);
            String str3 = createPool(list3,threadpool);
            System.out.println("str3:"+str3);
        }

        public static String createPool(List<String> list,ExecutorService threadpool){
            String result = "111";
            threadpool.execute(new CreateUser(result) {
                @Override
                public void run() {
                    String s = "";
                    for (String str : list) {
                        s = str+",";
                        System.out.println(Thread.currentThread().getName()+"===="+s);
                    }
                }
            });
            System.out.println("result:"+result);
            return result;
        }

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList();
        list.add("wulx");
        list.add("daihuiyi");
        list.add("wulimo");
        list.add("daianna");
        list.add("AAAAAAAAA");
        list.add("BBBBBBBB");
        list.add("CCCCCCCC");
        list.add("DDDDDDDD");
        list.add("EEEEEEEE");
        list.add("RRRRRRRR");
        createUser(list);
    }
}
