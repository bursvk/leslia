package com.leslia.redis;

import com.leslia.test.base.BaseTestLocal;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;


public class RedisTest extends BaseTestLocal {



    @Resource
    private RedisUtil redisUtil;
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void test1(){
        Student student=new Student();
        student.setAge("20");
        student.setName("suli");
        redisUtil.set("student",student);
    }

    @Test
    public void test2(){
      Student student= (Student) redisUtil.get("student");
        student.add();
      System.out.println(student.getName());
    }

    @Test
    public void test3(){
        redisUtil.set("des","the des which spring redis");
        String des=(String)redisUtil.get("des");
        System.out.println(des);
    }

}
