package com.pagoda.demo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.pagoda.demo.entity.Keywordrecord;
import com.pagoda.demo.utii.RedisConfigurtion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleWebApplicationTests {

    @Autowired
    @Qualifier("initRedisTemplate")
    private RedisTemplate redisTemplate;


    @Test
    public void test0() {
        System.out.println(redisTemplate.getStringSerializer());
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("cn:con:test","aa");
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
            long length = operations.size("con");
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
        // Predicate断言型接口,
        Predicate<String> predicate = x -> x.contains("pre");
        System.out.println("Predicate断言型接口:"+predicate.test("predicate"));
        // Supplier供给型接口，无入参，有出参，
        Supplier<String> supplier = () -> "Supplier供给型接口";
        System.out.println("supplier:"+supplier.get());
        // Function函数型接口，有入参，有出参
        Function<String,Integer> function = x -> Objects.isNull(x) ? 0:x.length();
        System.out.println("Function函数型接口:"+function.apply("consumer"));
    }

    /**
     * lambda表达式
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
}
