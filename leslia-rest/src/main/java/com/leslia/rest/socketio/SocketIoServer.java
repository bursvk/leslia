package com.leslia.rest.socketio;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class SocketIoServer {

    private static Logger logger= LoggerFactory.getLogger(SocketIoServer.class);

    private static SocketIOServer socketIOServer;
    //指定要主机ip地址，这个和页面请求ip地址一致
    private static final String hostName="localhost";
    //指定端口号
    private static final int port=9092;
    //设置最大的WebSocket帧内容长度限制
    private static final int maxFramePayloadLength=1024*1024;
    //设置最大HTTP内容长度限制
    private static final int maxHttpContentLength=1024 * 1024;
    /*
     * 添加客户端
     */
    public  void startSocketIo() throws InterruptedException {
        //配置
        Configuration conf = new Configuration();
        conf.setHostname(hostName);
        conf.setPort(port);
        conf.setMaxFramePayloadLength(maxFramePayloadLength);
        conf.setMaxHttpContentLength(maxHttpContentLength);
        socketIOServer = new SocketIOServer(conf);
        ConnectListener connect = new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                logger.info("添加client:{}",client.getSessionId());
            }
        };

        //添加客户端
        socketIOServer.addConnectListener(connect);
        socketIOServer.start();

        //设置超时时间
        Thread.sleep(Integer.MAX_VALUE);
        socketIOServer.stop();
    }

    /*
     * 全体消息推送
     * @param type 前台根据类型接收消息，所以接收的消息类型不同，收到的通知就不同
     * 推送的事件类型
     * @param content
     * 推送的内容
     */
    public void pushMessage(String type,String content) {
        //获取全部客户端
        Collection<SocketIOClient> allClients = socketIOServer.getAllClients();
        for (SocketIOClient socket : allClients) {
            socket.sendEvent(type, content);
        }
    }

    /*
     * 启动服务
     */
    public void startServer() {
        if (socketIOServer == null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        startSocketIo();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /*
     * 停止服务
     */
    public void stopServer() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            socketIOServer = null;
        }
    }


}
