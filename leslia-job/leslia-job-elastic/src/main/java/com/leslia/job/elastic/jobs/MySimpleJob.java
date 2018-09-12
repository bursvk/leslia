package com.leslia.job.elastic.jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySimpleJob implements SimpleJob {

    private Logger logger= LoggerFactory.getLogger(MySimpleJob.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.info("------Thread ID: {}, 任务总片数: {}, 当前分片项: {}",
                Thread.currentThread().getId(),
                shardingContext.getShardingTotalCount(),
               shardingContext.getShardingParameter());
    }


}
