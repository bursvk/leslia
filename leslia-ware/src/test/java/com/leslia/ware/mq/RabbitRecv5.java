package com.leslia.ware.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class RabbitRecv5 {


    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();

        channel.queueBind(queueName,EXCHANGE_NAME, "kern.*");
        channel.queueBind(queueName,EXCHANGE_NAME,"*.critical");

        System.out.println("waiting for messages ...");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("received message:" +
                    delivery.getEnvelope().getRoutingKey() + " " + message);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }



}
