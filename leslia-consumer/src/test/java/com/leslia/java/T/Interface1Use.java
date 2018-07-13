package com.leslia.java.T;

public class Interface1Use {

    //private Class T;

    public <T extends Interface1> void Interface1Use( T t) {
        t.getClass();
    }

    public static void aa(){
    }


    public static void main(String args[]){
    }




}

class Fruit{};
class Apple extends Fruit{};
class Jonathan extends Apple{};
class orange extends Fruit{};




