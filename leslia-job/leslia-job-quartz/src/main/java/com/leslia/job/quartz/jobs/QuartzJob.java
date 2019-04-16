package com.leslia.job.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzJob implements Job {

    private Logger logger= LoggerFactory.getLogger(QuartzJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("定时任务开启...{}",jobExecutionContext.toString());
    }

}
