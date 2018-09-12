package com.leslia.resta.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  /*  @Resource
    private MyWebSocketHandler myWebSocketHandler;*/

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(getMyWebSocketHandler(),"/echo");
    }

    @Bean
    public MyWebSocketHandler getMyWebSocketHandler(){
        return new MyWebSocketHandler();
    }





}
