package com.leslia.java.test;

import com.leslia.util.enums.EnumCode;
import com.leslia.util.exception.BaseException;
import com.leslia.util.exception.LogException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionTest {


    private Logger logger= LoggerFactory.getLogger(ExceptionTest.class);


/*    private void logException(BaseException e){
        StringWriter trace=new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        logger.error(trace.toString());
    }*/

    @Test
    public  void test1(){
        try{
            throw  new BaseException(EnumCode.FAIL_1);
        }catch (BaseException e){
            LogException.logger(logger,e);
            if(e.getErrorCode().equals(EnumCode.FAIL_1.getCode())){
                System.out.println(EnumCode.FAIL_1.getCode());
                System.out.println(EnumCode.FAIL_1.getMessage());
            }
        }
    }



    @Test
    public void test2(){
        try {
            throw new Exception("错误信息2");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try {
            //System.exit(0);
            throw new RuntimeException();
        }catch (RuntimeException e){
           e.printStackTrace();
        }finally {
            System.out.println("finally");
        }
    }


    @Test
    public void test4(){
        Integer code=000000;
        System.out.println(code);
    }




}
