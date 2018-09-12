package com.leslia.resta.listener;

import com.leslia.resta.socketio.SocketIoServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SocketIoListener implements ServletContextListener {

    private Logger logger= LoggerFactory.getLogger(SocketIoListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("启动SocketIo服务...");
        SocketIoServer socketIoServer = new SocketIoServer();
        socketIoServer.startServer();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("关闭SocketIo服务...");
        SocketIoServer socketIoServer = new SocketIoServer();
        socketIoServer.stopServer();
    }


}
