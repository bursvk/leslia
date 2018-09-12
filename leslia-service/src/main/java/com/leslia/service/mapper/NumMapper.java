package com.leslia.service.mapper;

import com.leslia.api.pojo.Num;

public interface NumMapper {

    public void insertNum(Num num);

    public Num selectNum(int id);

    public void updateNum(Num num);



}
