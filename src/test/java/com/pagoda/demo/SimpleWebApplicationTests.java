package com.pagoda.demo;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleWebApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        List<String> list = Lists.newArrayList();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        list.add("ee");
        redisTemplate.delete("con");
        ListOperations operations = redisTemplate.opsForList();
        list.forEach(n -> {
            long length = operations.size("con");
            operations.leftPush("con",n);
            if (length > 3){
                operations.rightPop("con");
            }
        });
        System.out.println(operations.range("con",0,-1));
    }



}
