package com.leslia.test.ehcache;

import com.leslia.test.pojo.Student;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EhcacheTest {

    private Logger logger= LoggerFactory.getLogger(EhcacheTest.class);

    @Test
    public void test1() throws Exception{
        CacheManager cacheManager=CacheManager.getInstance();
        Cache cache= cacheManager.getCache("HelloWorldCache");
        Element element=new Element("name","leslia");
        cache.put(element);
        Element element1=cache.get("name");
        System.out.println(element1.getObjectValue());
        Thread.sleep(4000);
        Element element2=cache.get("name");
        System.out.println(element2.getObjectValue());
    }


    @Test
    public void test2(){
        CacheManager cacheManager=CacheManager.getInstance();
        Cache cache=cacheManager.getCache("UserCache");
        Student student=new Student();
        student.setAge("19");
        student.setName("leslia");
        Element element=new Element("student",student);
        cache.put(element);
        Student student1=(Student) cache.get("student").getObjectValue();
        System.out.println(student1.getName()+""+student1.getAge());
    }



}
