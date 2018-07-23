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

    private MySessionContext myc=MySessionContext.getInstance();


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
        HttpSession session1=myc.getSession(session.getId());
        String name=(String)session1.getAttribute("name");
        System.out.println(myc.mymap.size());
        return name;
    }

    @RequestMapping("/getSessionV")
    @ResponseBody
    public void getSessionV(HttpServletRequest request){
        HttpSession session=request.getSession();
        logger.info(session.getId());
        session.invalidate();
    }



}
