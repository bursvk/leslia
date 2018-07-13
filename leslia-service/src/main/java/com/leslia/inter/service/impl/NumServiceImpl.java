package com.leslia.inter.service.impl;

import com.leslia.inter.api.NumService;
import com.leslia.inter.mapper.NumMapper;
import com.leslia.inter.pojo.Num;
import com.leslia.util.exception.BaseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
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



}
