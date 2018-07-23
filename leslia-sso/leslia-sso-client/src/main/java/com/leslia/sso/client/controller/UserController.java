package com.leslia.sso.client.controller;

import com.leslia.sso.client.base.BaseConfig;
import com.leslia.util.http.RequestUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger= LoggerFactory.getLogger(UserController.class);
    
    @RequestMapping("/receiveToken")
    public String receiveToken(HttpServletRequest request,@RequestParam String token, @RequestParam String userId){
        logger.info("接受令牌  校验令牌.....");
        HttpSession session=request.getSession();
        logger.info("sessionId:"+session.getId());
        session.setAttribute("isLogin",true);
        session.setAttribute("token",token);
        session.setAttribute("userId",userId);
        return "success";
    }

    @RequestMapping("/logout")
    public String pushLogOut(HttpServletRequest request){
        logger.info("推送登录注销......");
        HttpSession session=request.getSession();
        logger.info("sessionId;"+session.getId());
        session.invalidate();
        String backUrl = request.getRequestURL().toString();
        backUrl= RequestUrl.baseUrl(backUrl);
        logger.info("backUrl:"+backUrl);
        return "redirect:"+ BaseConfig.getSso_url()+"/sso/logout?backUrl="+backUrl;
    }







}
