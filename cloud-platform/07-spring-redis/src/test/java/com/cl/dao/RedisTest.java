package com.cl.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * by cl at 2020/3/28 0028
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString() {
        ValueOperations operations = redisTemplate.opsForValue();
        operations.set("name", "cl");
        System.out.println(operations.get("name"));
    }

    @Test
    public void testList() {
        ListOperations list = redisTemplate.opsForList();
        list.leftPush("testList","1");
        list.leftPush("testList","2");
        System.out.println(list.range("testList", 0, 10));
    }

    @Test
    public void testSet() {
        SetOperations set = redisTemplate.opsForSet();
        set.add("testSet", "张三");
        set.add("testSet", "李四");
        System.out.println(set.members("testSet"));
    }
    @Test
    public void testHash() {
       HashOperations hash =  redisTemplate.opsForHash();
       Map<String , Object> map = new HashMap<>();
       map.put("name", "cl");
       map.put("age", "11");
       hash.putAll("user01", map);
       System.out.println(hash.entries("user01"));
    }

}
