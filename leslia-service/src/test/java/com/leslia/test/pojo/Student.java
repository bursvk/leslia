package com.leslia.test.pojo;

import redis.clients.jedis.Jedis;

public class Student {

    private String name;

    private String age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public void add(){
        System.out.println("add");
    }


    public static void main(String args[]){
        Jedis jedis=new Jedis("39.105.98.191",6379);
        jedis.auth("Leslia000");
        String value=jedis.get("name");
        System.out.println(value);

        Student student=new Student();
        student.setName("");
    }


}
