package com.leslia.ware.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class RabbitRecv4 {

    private static final String EXCHANGE_NAME = "direct_logs";
    //private static final String TASK_QUEUE_NAME="task_queue_1";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String queueName = channel.queueDeclare().getQueue();
        //channel.queueDeclare(queueName, false, false, false, null);

        channel.queueBind(queueName, EXCHANGE_NAME, "info");
        channel.queueBind(queueName,EXCHANGE_NAME,"error");
        System.out.println("waiting for messages...");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("received message:" +
                    delivery.getEnvelope().getRoutingKey() + " " + message);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }


}
