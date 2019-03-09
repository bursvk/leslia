package com.leslia.ware.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class RabbitRecv2 {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println("waiting for messages...");

        channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            System.out.println("received message:" + message);
            try {
                doWork();
            } finally {
                //消息确认
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                //否定确认 false 丢失  true  重新排列
                //channel.basicReject(delivery.getEnvelope().getDeliveryTag(),true);
                //重新排列
                //channel.basicNack(delivery.getEnvelope().getDeliveryTag(),true,true);
            }
        };
        channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> { });
    }

    private static void doWork() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException _ignored) {
            Thread.currentThread().interrupt();
        }
    }

}
