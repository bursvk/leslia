package com.leslia.test.redis;


import com.leslia.redis.RedisUtil;
import com.leslia.test.pojo.Student;
import com.leslia.util.data.RedisKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/redis.xml"})
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
    public void incr(){
       long userId= redisUtil.incr(RedisKey.userId,1);
       System.out.println(userId);
    }







}
