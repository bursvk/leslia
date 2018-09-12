package com.leslia.consumer.jstorm;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CountBolt implements IRichBolt{

    private static Logger logger= LoggerFactory.getLogger(CountBolt.class);

    public CountBolt() {
        System.out.println("CountBolt:**********************************");

    }
    Map<String,Integer> map=new HashMap<>();
    private FileWriter writer;

    /**
     * prepare 在这里仅仅是启动了一个文件写的定时线程，每2秒将结果写到文件中，并清空map.
     */
    @Override
    public void prepare(Map stormConf, TopologyContext topologyContext, OutputCollector outputCollector) {
        logger.info("数据处理....");
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        try {
            //以文件追加的方式打开文件
            writer = new FileWriter("e://my_log1.txt",true);
            //writer = new FileWriter("D://data/jstorm/word.txt",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //开启定时线程
        pool.scheduleAtFixedRate(()->{
            try {
                writer.write("\r\n");
                writer.write("***************************************");
                logger.info("写入文件...");
                for(Map.Entry<String, Integer> entry:map.entrySet()) {
                    writer.write(entry.getKey()+" : "+entry.getValue());
                    writer.write("\r\n");
                    System.out.println(entry.getKey()+" : "+entry.getValue());
                }
                writer.flush();
                map.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, 2000L, 2000L, TimeUnit.MILLISECONDS);
    }


    /**
     * 收到 spout 发送来的 Word 进行统计
     */
    @Override
    public void execute(Tuple tuple) {
        logger.info("收到信息，execute");
        String word=tuple.getString(0);
        if(map.get(word)==null) {
            map.put(word, 1);
        }else {
            map.put(word, map.get(word)+1);
        }
    }

    /**
     * Topology 被 shutdown时会被触发的动作，我们可以用来做一些清理工作
     */
    @Override
    public void cleanup() {
        logger.info("*******************public void cleanup()");
        for(Map.Entry<String, Integer> entry:map.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
        map.clear();
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }


}
