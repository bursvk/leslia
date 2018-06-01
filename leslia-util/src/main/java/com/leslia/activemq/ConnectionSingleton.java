package com.leslia.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;

public class ConnectionSingleton {

    private static final String USERNAME= ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD= ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKER_URL="failover://tcp://39.105.98.191:61616";

    private static Connection connection;


    static{
        try {
            ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKER_URL);
            connection=connectionFactory.createConnection();
            connection.start();
        }catch (Exception e){
        }
    }

    public static Connection connectionSingle(){
        return connection;
    }




}
