package com.leslia.test.base;

import com.leslia.java.test.Base;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class User {

    public String userName="root";

    public String password="root";

    public void aa(){
        System.out.println("my name is ...");
    }

    @Autowired
    private Base base=new Base() {
        @Override
        public String getPassword() {
            return null;
        }
    };

    @Test
    public void bb(){
        Base base=new Base(){
            @Override
            public String getPassword() {
                return null;
            }
        };
        base.getUsername();
    }

}
