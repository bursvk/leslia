package com.leslia.job.elastic.jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DataFlowJob implements DataflowJob<String> {

    private Logger logger= LoggerFactory.getLogger(DataFlowJob.class);

    private int num=1;

    @Override
    public List<String> fetchData(ShardingContext shardingContext) {
        List<String> list=new ArrayList<>();
        list.add("first");
        list.add("two");
        list.add("three");
        return list;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<String> list) {
        logger.info("处理...");
        for (String s:list){
            logger.info(s);
        }
    }


}
