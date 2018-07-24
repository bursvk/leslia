package com.leslia.rest.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/session")
public class SessionController {

    private Logger logger= LoggerFactory.getLogger(SessionController.class);


    @RequestMapping("/setSession")
    @ResponseBody
    public void save(HttpServletRequest request){
        HttpSession session=request.getSession();
        logger.info("sessionId:"+session.getId());
        session.setAttribute("name","leslia");
    }

    @RequestMapping("/getSession")
    @ResponseBody
    public String getSession(HttpServletRequest request){
        HttpSession session=request.getSession();
        logger.info("sessionId:"+session.getId());
        return "";
    }

    @RequestMapping("/getSessionV")
    @ResponseBody
    public void getSessionV(HttpServletRequest request){
        HttpSession session=request.getSession();
        logger.info(session.getId());
        session.invalidate();
    }



}
