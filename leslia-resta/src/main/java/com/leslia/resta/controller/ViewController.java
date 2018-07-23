package com.leslia.resta.controller;

import org.jasig.cas.client.util.AssertionHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/view")
public class ViewController {

    private Logger logger= LoggerFactory.getLogger(ViewController.class);

    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        HttpSession session=request.getSession();
        logger.info("sessionId"+session.getId());
        String username=request.getRemoteUser();
        logger.info("username:"+username);
        String username1= AssertionHolder.getAssertion().getPrincipal().getName();
        logger.info("username1:"+username1);
        return "index";
    }

    @RequestMapping("/success")
    public String success(){
        return "success";
    }


}
