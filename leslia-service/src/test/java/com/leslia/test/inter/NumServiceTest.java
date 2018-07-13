package com.leslia.test.inter;

import com.alibaba.dubbo.config.annotation.Reference;
import com.leslia.inter.api.NumService;
import com.leslia.inter.pojo.Num;
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
    public void updateNum(){
        Num num=numService.selectNum(1);
        numService.updateNum(num);
    }



}
