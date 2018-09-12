package com.leslia.consumer.jstorm;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class WordSpout implements IRichSpout{

    private static Logger logger= LoggerFactory.getLogger(WordSpout.class);

    private String[] strs= {"one","two","three","four","five","six"};

    SpoutOutputCollector collector;

    public WordSpout() {
        super();
        System.out.println("WordSpout 构造方法 ...");
    }

    /**
     * 打开 stream 流资源，只会执行一次
     * @param map
     * @param topologyContext
     * @param spoutOutputCollector
     */
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        logger.info("打开stream资源 ....");
        this.collector=spoutOutputCollector;
    }

    @Override
    public void close() {

    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }

    /**
     * 循环执行，向外发送 Tuple
     */
    @Override
    public void nextTuple() {
        int index= RandomUtils.nextInt(6);
        collector.emit(new Values(strs[index]));
        logger.info("向外发送，nextTuple:{}",strs[index]);
    }

    @Override
    public void ack(Object o) {

    }

    @Override
    public void fail(Object o) {

    }

    /**
     * 定义发射的字段类型，是第一个要是执行的方法。
     * @param outputFieldsDeclarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        logger.info("declareOutputFields ....");
        outputFieldsDeclarer.declare(new Fields("word"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }


}
