package com.leslia.job.schedule.jobs;

import com.alibaba.dubbo.config.annotation.Reference;
import com.leslia.api.api.OrderCopyService;
import com.leslia.api.api.OrderService;
import com.leslia.api.pojo.Order;
import com.leslia.api.pojo.OrderCopy;
import com.taobao.pamirs.schedule.IScheduleTaskDealMulti;
import com.taobao.pamirs.schedule.TaskItemDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class TaskOrderMulti implements IScheduleTaskDealMulti<Order> {

    private Logger logger= LoggerFactory.getLogger(TaskOrderMulti.class);

    @Reference
    private OrderService orderService;

    @Reference
    private OrderCopyService orderCopyService;

    @Override
    public boolean execute(Order[] orders, String s) throws Exception {
        OrderCopy orderCopy=new OrderCopy();
        for (Order order:orders){
            orderCopy.setBillNumber(order.getBillNumber());
            orderCopy.setBuildDate(order.getBuildDate());
            orderCopy.setCustomer(order.getCustomer());
            orderCopy.setGoodsName(order.getGoodsName());
            orderCopy.setAmount(order.getAmount());
            orderCopy.setSaleMoney(order.getSaleMoney());
            orderCopyService.insert(orderCopy);
        }
        return true;
    }

    @Override
    public List<Order> selectTasks(String taskParameter, String ownSign, int taskQueueNum,
                                   List<TaskItemDefine> taskItemList, int eachFetchDataNum) throws Exception {
        logger.info("taskParameter:{},ownSign:{},taskQueueNum:{},taskItemList:{},eachFetchDataNum:{}",
                taskParameter,ownSign,taskQueueNum,taskItemList,eachFetchDataNum);
        if(taskItemList==null||taskItemList.size()==0){
            return null;
        }
        List<String> idList=new ArrayList<>();
        for (TaskItemDefine taskItemDefine:taskItemList){
            idList.add(taskItemDefine.getTaskItemId());
        }
        Map<String,Object> map=new HashMap<>();
        map.put("idList",idList);
        map.put("limit",eachFetchDataNum);
        List<Order> orderList=orderService.getCopyList(map);
        logger.info("idList：{},查询记录数：{}",orderList.size(),idList);
        return orderList;
    }

    @Override
    public Comparator<Order> getComparator() {
        return null;
    }


}
