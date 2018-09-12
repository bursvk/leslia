package com.leslia.service.mapper;


import com.leslia.api.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderMapper {

    int insert(Order record);

    List<Order> getCopyList(Map<String,Object> map);

}