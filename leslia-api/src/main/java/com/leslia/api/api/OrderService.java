package com.leslia.api.api;

import com.leslia.api.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {

    void insert(Order order);

    List<Order> getCopyList(Map<String,Object> map);

}
