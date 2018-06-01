package com.leslia.activemq;

import javax.jms.*;

public class Consumer {


    public static void main(String args[]){
        try{
            Connection connection=ConnectionSingleton.connectionSingle();
            Session session=connection.createSession(true,Session.CLIENT_ACKNOWLEDGE);
            Destination destination=session.createQueue("wechat");
            MessageConsumer consumer=session.createConsumer(destination);
            while(true){
                TextMessage message=(TextMessage) consumer.receive();
                System.out.println("收到的信息是"+message.getText());
                message.acknowledge();
                session.commit();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
