package com.leslia.rest.controller;

import com.leslia.rest.socketio.SocketIoServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/socket")
public class SocketController {

    @ResponseBody
    @RequestMapping("/pushMessage")
    public void pushMessage(){
        SocketIoServer socketIoServer=new SocketIoServer();
        socketIoServer.pushMessage("connect_msg","今天下午2点开会");
    }

}
