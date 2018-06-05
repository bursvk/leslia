package com.leslia.inter.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.leslia.inter.api.InitDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/inter")
public class InterController {

    private Logger logger= LoggerFactory.getLogger(InterController.class);

    @Reference
    private InitDemoService initDemoService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        String contextPath = request.getSession().getServletContext().getRealPath("/");
        logger.info(contextPath);
        return "index";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam String name){
        logger.info("name:{}",name);
        String backName="";
        try{
            backName=initDemoService.helloWorld(name);
            logger.info("backName:{}",backName);
        }catch (Exception e){
            logger.error("this witch dubbo service error",e);
        }
        return backName;
    }



}
