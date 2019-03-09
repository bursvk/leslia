package com.leslia.resta.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

    private Logger logger= LoggerFactory.getLogger(ViewController.class);

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/user")
    public String user(){
        return "user";
    }

    @RequestMapping("/admin")
    public String admin(){
        Session session= SecurityUtils.getSubject().getSession();
        String username=(String)session.getAttribute("username");
        logger.info("username:"+username);
        return "admin";
    }


    @RequestMapping("/success")
    public String success(){
        return "success";
    }

    @RequestMapping("/fail")
    public String fail(){
        return "fail";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }

    @RequestMapping("/webSocket")
    public String webSocket(){
        return "webSocket";
    }

    @RequestMapping("/socketIo")
    public String socketIo(){
        return "socketIo";
    }



}
