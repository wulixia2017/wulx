package com.pagoda.demo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.pagoda.demo.entity.Keywordrecord;
import com.pagoda.demo.entity.Member;
import com.pagoda.demo.entity.Singleton;
import org.assertj.core.util.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleWebApplicationTests {

    @Autowired
    @Qualifier("initRedisTemplate")
    private RedisTemplate redisTemplate;

    @Test
    public void test3() {
        //单例模式
        Singleton singleton = Singleton.getInstance();
        singleton.setName("name");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Singleton newSington = Singleton.getInstance();
                System.out.println(Thread.currentThread().getName()+" newSington:  "+newSington.getName());
            }
        }).start();
        System.out.println(Thread.currentThread().getName()+" singleton:  "+singleton.getName());
    }

    @Test
    public void test0() {
        //String类型
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("String:test","aa");
        //hash
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.hasKey("hash:test","hash");
        //sets
        Set<String> stringSet = Sets.newHashSet();
        stringSet.add("bb");
        stringSet.add("cc");
        stringSet.add("aa");
        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add("sets:test",stringSet);
        //list
        //sorted sets
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        long i = 0;
        for (String str : stringSet){
            zSetOperations.add("zsets:test", str, i++);
        }
        System.out.println(zSetOperations.range("zsets:test",0,-1));
    }

    /**
     * redis存储集合
     */
    @Test
    public void test() {
        List<String> list = Lists.newArrayList();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        list.add("ee");
        redisTemplate.delete("com");
        ListOperations operations = redisTemplate.opsForList();
        list.forEach(n -> {
            long length = operations.size("com");
            operations.leftPush("com",n);
            if (length > 3){
                operations.rightPop("com");
            }
        });
        System.out.println(operations.range("com",0,-1));
    }

    /**
     * jdk1.8的四大内置核心函数式接口
     */
    @Test
    public void test1(){
        // Consumer消费型接口，只消费，不返回，
        List<String> consumerList = Lists.newArrayList();
        consumerList.add("consumer");
        consumerList.add("consumer1");
        consumerList.add("consumer2");
        Consumer<List<String>> consumer =
                x -> { List<String> stringList1 = x;for (String  str : stringList1){ System.out.println("Consumer消费型接口:"+str); } };
        consumer.accept(consumerList);
        // Predicate断言型接口, String 入参
        Predicate<String> predicate = x -> x.contains("pre");
        System.out.println("Predicate断言型接口:"+predicate.test("predicate"));
        // Supplier供给型接口，无入参，有出参，  String 出参
        Supplier<String> supplier = () -> "Supplier供给型接口";
        System.out.println("supplier:"+supplier.get());
        // Function函数型接口，有入参，有出参,,,,,,String 入参，Integer 出参
        Function<String,Integer> function = x -> Objects.isNull(x) ? 0:x.length();
        System.out.println("Function函数型接口:"+function.apply("consumer"));
    }

    /**
     * lambda表达式
     * Java 8 发布的最重要新特性。
     * Lambda 允许把函数作为一个方法的参数
     * 使用 Lambda 表达式可以使代码变的更加简洁紧凑
     */
    @Test
    public void test2(){
        List<String> stringList = Lists.newArrayList();
        stringList.add("test");
        stringList.add("tt");
        stringList.add("1t");
        stringList.add("bt");
        stringList.add("bt");
        List<Integer> list = stringList.stream().map(x -> x.contains("test")?0:x.length()).collect(Collectors.toList());
        System.out.println("list:"+JSON.toJSONString(list));
        List<String> list1 = stringList.stream().filter(x -> x.contains("tes")).collect(Collectors.toList());
        System.out.println("list1:"+JSON.toJSONString(list1));
        List<String> list2 = stringList.stream().sorted().collect(Collectors.toList());
        System.out.println("list2:"+JSON.toJSONString(list2));
        List<Keywordrecord> keywordrecords = Lists.newArrayList();
        Map<String,Keywordrecord> stringMap = keywordrecords.stream().collect(Collectors.toMap((key -> key.getKeyrecord()),(key -> key), (x,y) -> x));
        System.out.println("stringMap:"+JSON.toJSONString(stringMap));
        BinaryOperator<String> binaryOperator = (x,y) -> x;
        stringList.stream().collect(Collectors.toMap(x -> x, x -> x,binaryOperator));
        String[] string = stringList.stream().toArray(String[]::new);
        System.out.println("string:"+JSON.toJSONString(string));
        List<String> strList = stringList.stream().collect(Collectors.toList());
        System.out.println("strList:"+JSON.toJSONString(strList));
        List<String> aaList = stringList.stream().filter(x -> x.contains("t")).distinct().sorted().collect(Collectors.toList());
        System.out.println("aaList:"+JSON.toJSONString(aaList));
    }

    /**
     * jdk1.8 stream流
     * 高级的迭代器
     * 每次转换不会改变原数据，返回一个新的Stream对象
     * 传统方式，java代码不得不依赖于关系型数据库的聚合函数，对数据进行一些统计等处理
     * 简单快捷，代码简洁
     * 与jdk1.8四大接口配合使用，对集合类进行过滤，组合，统计，排序等操作
     */
    @Test
    public void  test5(){
        List<Integer> stringList = Lists.newArrayList();
        stringList.add(4);
        stringList.add(2);
        stringList.add(3);
        stringList.add(1);
        //排序
        List<Integer> aList = stringList.stream().sorted(Integer::compareTo).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(aList));
        List<Integer> bList = stringList.stream().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -1;
            }
        }).collect(Collectors.toList());
        System.out.println("b:"+JSON.toJSONString(bList));
        //排序
        List<Integer> cList = stringList.stream().filter(i -> i<3).sorted(Integer::compareTo).collect(Collectors.toList());
        System.out.println("C:"+JSON.toJSONString(cList));
        List<Member> members = Lists.newArrayList();
        Member member1 = new Member();
        member1.setAge("1111");member1.setId(1);member1.setNickName("aaa");
        Member member2 = new Member();
        member2.setAge("2222");member2.setId(2);member2.setNickName("bbb");
        Member member3 = new Member();
        member3.setAge("3333");member3.setId(3);member3.setNickName("ccc");
        Member member4 = new Member();
        member4.setAge("4444");member4.setId(4);member4.setNickName("ddd");
        Member member5 = new Member();
        member5.setAge("5555");member5.setId(5);member5.setNickName("eee");
        members.add(member1);
        members.add(member2);
        members.add(member3);
        members.add(member4);
        members.add(member5);
        //转换map
//        Map<String, String> memberMap1 = members.stream().collect(Collectors.toMap(Member::getNickName, Member::getAge));
//        System.out.println("memberMap1:"+JSON.toJSONString(memberMap1));
        //静态工厂方法获取实例，new 实例重写方法，等等
        //给list排序，去重，转换map
        List<Member> oneList = members.stream().filter(member -> member.getId()>=3).collect(Collectors.toList());
        System.out.println("oneList:"+JSON.toJSONString(oneList));
        List<Member> twoList = oneList.stream().sorted(Comparator.comparing(Member::getId).reversed().thenComparing(Member::getAge)).collect(Collectors.toList());
        System.out.println("twoList:"+JSON.toJSONString(twoList));
        Map<String, Member> memberMap3 = twoList.stream().collect(Collectors.toMap(Member::getNickName, member -> {return member;}));
        System.out.println("memberMap3:"+JSON.toJSONString(memberMap3));
        Map<String, Member> memberMap2 = members.stream().filter(member -> member.getId()>=1).sorted(Comparator.comparing(Member::getId).reversed().thenComparing(Member::getAge))
               .collect(Collectors.toMap(Member::getNickName, member -> {return member;}));
        System.out.println("memberMap2:"+JSON.toJSONString(memberMap2));
        List<Member> memberList = members.stream().filter(member -> member.getId()>1 || member.getNickName().contains("e")).collect(Collectors.toList());
        System.out.println("memberList:"+JSON.toJSONString(memberList));
        Function<Member,String> keyFunction =  member -> member.getNickName();
        members.stream().sorted(Comparator.comparing((member) -> {return member.getId();}));

        Map<Integer,Member> map1 = members.stream().filter((Member member)-> member.getId() > 2)
                .sorted(Comparator.comparing(Member::getId))
                .collect(Collectors.toMap((Member member)->{return member.getId();},(Member member)->{return member;}));
        System.out.println("map1:"+JSON.toJSONString(map1));
    }

    @Test
    public void test10(){
        Object i = 10;
        Object n = null;
        System.out.println("------"+String.valueOf(n));
        System.out.println(i.toString());
        System.out.println(String.valueOf(i));
        try{
            System.out.println((String)i);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("==============");
            System.out.println("错误error："+e);
            System.out.println("==============");
            System.out.println("错误error："+e.getStackTrace());
        }
    }

    @Test
    public void test6(){
        Boolean a = null;
        Boolean b = true;
        Boolean bc = false;
        System.out.println(Optional.ofNullable(a).orElse(false));
        System.out.println(Optional.ofNullable(b).orElse(false));
        System.out.println(Optional.ofNullable(bc).orElse(false));

        Optional.of("aaa").orElseGet(()->"bbb");
        Optional.ofNullable("aaa").orElse("bb");
    }

    @Test
    public void test7(){
        System.out.println(Integer.MAX_VALUE);
//        String registerTime = "20201010102233";
//        String onlineTime = "2020-10-10 10:22";
//        String onlineTime1 = "2020-10-10 10:22:00";
//        long register = convertLongTime(registerTime, DateUtils.DATETIME_SHORT_PATTERN);
//        long online = convertLongTime(onlineTime, DateUtils.DATETIME_PATTERN_CREAT_ORDER);
//        long online1 = convertLongTime(onlineTime1, DateUtils.DATETIME_PATTERN_CREAT_ORDER);
//        System.out.println(register);
//        System.out.println(online);
//        System.out.println(online1);
    }


    public  Long convertLongTime(String time, String pattern) {
        Date d = convertDate(time, pattern);
        return d.getTime();
    }

    public Date convertDate(String date, String pattern) {
        try {
            if (!StringUtils.isEmpty(pattern) && !StringUtils.isEmpty(date)) {
                SimpleDateFormat df = new SimpleDateFormat(pattern.trim());
                return df.parse(date.trim());
            } else {
                String msg = "the date or pattern is empty.";
                throw new IllegalArgumentException(msg);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

}
