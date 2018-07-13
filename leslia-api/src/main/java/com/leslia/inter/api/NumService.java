package com.leslia.inter.api;

import com.leslia.inter.pojo.Num;

public interface NumService {

    public void insertNum(Num num);

    public Num selectNum(int id);

    public void updateNum(Num num);

}
