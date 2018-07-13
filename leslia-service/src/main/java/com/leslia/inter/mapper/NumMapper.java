package com.leslia.inter.mapper;

import com.leslia.inter.pojo.Num;

public interface NumMapper {

    public void insertNum(Num num);

    public Num selectNum(int id);

    public void updateNum(Num num);



}
