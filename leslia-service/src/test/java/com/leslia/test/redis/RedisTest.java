package com.leslia.test.redis;


import com.leslia.test.pojo.Student;
import com.leslia.util.data.RedisKey;
import com.leslia.ware.redis.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/redis.xml","classpath:spring/application.xml"})
public class RedisTest {

    @Resource
    private RedisUtil redisUtil;

    @Test
    public void test1(){
        redisUtil.hset("springHash","key1","value2");
        Student student=new Student();
        student.setName("suli");
        student.setAge("19");
        redisUtil.hset("springHash","key2",student);
        Set<String> set=new HashSet<>();
        set.add("url1");
        set.add("url2");
        set.add("url3");
        redisUtil.hset("springHash","key3",set);
    }

    @Test
    public void Test2(){
        System.out.println(redisUtil.hasKey("springHash"));
        System.out.println(redisUtil.hget("springHash","key1"));
        Map<Object,Object> map=redisUtil.hmget("springHash");
        Student student=(Student)map.get("key2");
        System.out.println(map);
        System.out.println(student.getAge() + student.getName());
    }

    @Test
    public void test3(){
        redisUtil.sSet("springSet",1);
        redisUtil.sSet("springSet",2);
        Set<Object> set=redisUtil.sGet("springSet");
        System.out.println(set);
    }

    @Test
    public void test4(){
      Long time=  redisUtil.getExpire("springSet");
      System.out.println(time);
      redisUtil.lSet("sList","redis");
      List<Object> list=new ArrayList<>();
      list.add("mysql");
      list.add("zookeeper");
      Student student=new Student();
      student.setName("suli");
      student.setAge("10");
      list.add(student);
      redisUtil.lSet("sList",list);
    }

    @Test
    public void test5(){
        String ticket="/bUwkuGKcc7k7Y48WTpSBQ==";
        Object ObjectUrls=redisUtil.hget(RedisKey.SSO_TICKET_URL,ticket);
        Set<String> set=ObjectUrls==null?new HashSet<>():(HashSet<String>)ObjectUrls;
        System.out.println(set);
    }




    @Test
    public void inc(){
       long userId= redisUtil.incr(RedisKey.USER_ID,1);
       System.out.println(userId);
    }








}
