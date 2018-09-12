package com.leslia.api.api;

import com.leslia.api.pojo.Num;

public interface NumService {

    public void insertNum(Num num);

    public Num selectNum(int id);

    public void updateNum(Num num);

    public void updateNum1(Num num);

}
