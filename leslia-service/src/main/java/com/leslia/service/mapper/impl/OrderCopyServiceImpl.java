package com.leslia.service.mapper.impl;

import com.leslia.api.api.OrderCopyService;
import com.leslia.api.pojo.OrderCopy;
import com.leslia.service.mapper.OrderCopyMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
public class OrderCopyServiceImpl implements OrderCopyService {

    @Resource
    private OrderCopyMapper orderCopyMapper;

    @Override
    public void insert(OrderCopy orderCopy) {
        orderCopyMapper.insert(orderCopy);
    }


}
