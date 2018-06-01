package com.leslia.activemq;

import javax.jms.*;

public class Producter {


    public static void main(String args[]){
        try{
            Connection connection=ConnectionSingleton.connectionSingle();
            Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            Destination destination=session.createQueue("wechat");
            MessageProducer producer=session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            for(int i=0;i<10;i++){
                TextMessage message=session.createTextMessage("hello world,我是苏里"+i);
                producer.send(message);
            }
            //session.commit();
            session.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
