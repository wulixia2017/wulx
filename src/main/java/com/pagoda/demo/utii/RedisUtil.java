package com.pagoda.demo.utii;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Objects;

public class RedisUtil {

    //获取用户近十次的输入内容
    public static List<String> getMemberRecentOutList(String key, String value, RedisTemplate redisTemplate) {
        ListOperations operations = redisTemplate.opsForList();
            long length = operations.size(key);
            if (Objects.nonNull(value)){
                operations.leftPush(key,value);
                if (length >= ConstantUtil.KEYWORD_LENGTH){
                    operations.rightPop(key);
                }
            }
        return operations.range(key,0,-1);
    }
}
