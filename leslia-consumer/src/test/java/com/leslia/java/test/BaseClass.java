package com.leslia.java.test;

import com.leslia.java.T.Student;
import com.leslia.util.exception.BaseException;
import org.junit.Test;

public class BaseClass extends Student{

    @Test
    public  void a() throws Exception{
        synchronized (this){
            System.out.println(1);
           Thread.sleep(2000);
        }
    }




    public void aa() throws BaseException {

    }

    public static void main(String args[]) throws Exception{
        BaseClass baseClass=new BaseClass();
        BaseClass baseClass1=new BaseClass();
        baseClass.a();
        baseClass1.a();
    }

    @Override
    public void username() {
        super.username();
    }
    
}
