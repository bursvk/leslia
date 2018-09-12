package com.leslia.java.test;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class thridClass extends BaseClass {

    public void aa(){
    }

    @Test
    public void test(){
        String s=" ";
        System.out.println(StringUtils.isBlank(s));
        System.out.println(StringUtils.isEmpty(s));
    }



}
