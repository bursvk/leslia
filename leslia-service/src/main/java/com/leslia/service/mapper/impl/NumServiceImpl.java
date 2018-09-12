package com.leslia.service.mapper.impl;

import com.leslia.api.api.NumService;
import com.leslia.api.pojo.Num;
import com.leslia.service.mapper.NumMapper;
import com.leslia.util.exception.BaseException;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Transactional
public class NumServiceImpl implements NumService {

    @Resource
    private NumMapper numMapper;

    int n=500;

    @Override
    public void insertNum(Num num){
        numMapper.insertNum(num);
    }

    @Override
    public Num selectNum(int id) {
       return numMapper.selectNum(id);
    }

    @Override
    public void updateNum(Num num) {
        int a=--n;
        System.out.println("a:"+a);
        num.setNum(a);
        numMapper.updateNum(num);
        if(n==498){
            throw new BaseException();
        }
    }

    @Override
    public void updateNum1(Num num) {
        numMapper.updateNum(num);
    }
}
