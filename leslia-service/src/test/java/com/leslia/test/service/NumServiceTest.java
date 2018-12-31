package com.leslia.test.inter;

import com.alibaba.dubbo.config.annotation.Reference;
import com.leslia.api.api.NumService;
import com.leslia.api.pojo.Num;
import com.leslia.test.base.BaseTestDubbo;
import org.junit.Test;

import java.util.Date;

public class NumServiceTest extends BaseTestDubbo{

    @Reference
    private NumService numService;

    @Test
    public void insertNum(){
        Num num=new Num();
        num.setCode("transactional");
        num.setNum(500);
        num.setDate(new Date());
        numService.insertNum(num);
    }

    @Test
    public void selectNum(){
        Num num=numService.selectNum(1);
        System.out.println(num.getNum());
    }

    @Test
    public void updateNum(){
        Num num=numService.selectNum(1);
        num.setNum(num.getNum()-1);
        numService.updateNum1(num);
    }



}
