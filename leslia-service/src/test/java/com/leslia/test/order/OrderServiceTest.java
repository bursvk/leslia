package com.leslia.test.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.leslia.api.api.OrderService;
import com.leslia.api.pojo.Order;
import com.leslia.ware.base.BaseTestDubbo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceTest extends BaseTestDubbo {

    @Reference
    private OrderService orderService;

    @Test
    public void insert(){
        Order order=new Order();
        order.setCustomer("A");
        order.setGoodsName("咖啡");
        order.setAmount(3.5);
        order.setSaleMoney(560);
        for(int i=0;i<=300;i++){
            order.setBillNumber("20141208000"+i);
            order.setBuildDate("20141208");
            orderService.insert(order);
        }
    }

    @Test
    public void list(){
        List<String>  list=new ArrayList<>();
        list.add("1");
        list.add("2");
        Map<String,Object> map=new HashMap<>();
        map.put("idList",list);
        map.put("limit",10);
        List<Order> orders=orderService.getCopyList(map);
        System.out.println(orders.size());
    }


}
