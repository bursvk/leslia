package com.leslia.service.mapper.impl;

import com.leslia.api.api.OrderService;
import com.leslia.api.pojo.Order;
import com.leslia.service.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Transactional
public class OrderServiceImpl implements OrderService {

    private Logger logger= LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private OrderMapper orderMapper;

    @Override
    public void insert(Order order) {
        orderMapper.insert(order);
    }

    @Override
    public List<Order> getCopyList(Map<String, Object> map) {
        return orderMapper.getCopyList(map);
    }

}
