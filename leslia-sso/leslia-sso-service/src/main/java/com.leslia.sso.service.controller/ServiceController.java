package com.leslia.sso.service.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.leslia.api.api.UserService;
import com.leslia.api.pojo.User;
import com.leslia.util.crypt.AESKey;
import com.leslia.util.crypt.AESUtil;
import com.leslia.util.exception.BaseException;
import com.leslia.util.http.HttpRequest;
import com.leslia.util.http.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/sso")
public class ServiceController {

    private Logger logger= LoggerFactory.getLogger(ServiceController.class);

    @Reference
    private UserService userService;

    @RequestMapping("/index")
    public void index(HttpServletRequest request){
        HttpSession session=request.getSession();
        logger.info("sessionId:"+session.getId());
    }


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpServletRequest request){
        String backUrl=request.getParameter("backUrl");
        HttpSession session=request.getSession();
        if(session.getAttribute("isLogin")==null) {
            session.setAttribute("backUrl",backUrl);
            return "login";
        }
        String ticket=(String) session.getAttribute("ticket");
        String username=(String) session.getAttribute("username");
        logger.info("用户已经登录 username:{},ticket:{},backUrl:{}",username,ticket,backUrl);
        backUrl=String.format(backUrl+"?ticket=%s",ticket);
        return "redirect:"+backUrl;
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request, @RequestParam String username,@RequestParam String password){
        if(username.equals("")||password.equals("")){
            return "login";
        }
        User user= userService.getUser(username,password);
        if(user==null){
            return "login";
        }
        String ticket="";
        HttpSession session=request.getSession();
        if(session.getAttribute("isLogin")!=null&&(Boolean) session.getAttribute("isLogin")){
            ticket=(String)session.getAttribute("ticket");
        }else {
            ticket = AESUtil.aesEncode(AESKey.SSO_TICKET,session.getId());
            session.setAttribute("isLogin",true);
            session.setAttribute("username",username);
            session.setAttribute("ticket",ticket);
            SessionUtil.add(session);
        }
        String backUrl=(String)session.getAttribute("backUrl");
        logger.info("用户登录成功 username:{},ticket:{},backUrl:{}",username,ticket,backUrl);
        backUrl=String.format(backUrl+"?ticket=%s",ticket);
        return "redirect:"+backUrl;
    }


    @RequestMapping("/verifyTicket")
    public void verifyTicket(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ticket=request.getParameter("ticket");
        ticket=ticket.replace(' ','+');
        String url=request.getParameter("url");
        String sessionId=AESUtil.aesDecode(AESKey.SSO_TICKET,ticket);
        HttpSession session= SessionUtil.get(sessionId);
        PrintWriter printWriter=response.getWriter();
        if(session!=null&&(Boolean) session.getAttribute("isLogin")){
            String username=(String)session.getAttribute("username");
            saveClientUrls(session,ticket,url);
            printWriter.write(username);
        }else {
            printWriter.write("");
        }
        printWriter.close();
    }


    public void saveClientUrls(HttpSession session,String ticket,String clientUrl){
        synchronized (session) {
            Object urlValues = session.getAttribute(ticket);
            if (urlValues == null) {
                Set<String> set = new HashSet<>();
                set.add(clientUrl);
                session.setAttribute(ticket, set);
            } else {
                Set<String> set = (Set<String>) urlValues;
                set.add(clientUrl);
            }
            logger.info("客户端注册 urls:{}",session.getAttribute(ticket).toString());
        }
    }


    @RequestMapping("/logout")
    public String logOut(HttpServletRequest request){
        String ticket=request.getParameter("ticket");
        ticket=ticket.replace(' ','+');
        String url=request.getParameter("url");
        String backUrl=request.getParameter("backUrl");
        logger.info("客户端登出发起 url:{}",url);
        String sessionId=AESUtil.aesDecode(AESKey.SSO_TICKET,ticket);
        HttpSession session=SessionUtil.get(sessionId);
        if(session!=null&&(Boolean) session.getAttribute("isLogin")){
            HttpRequest.sendPost(url,"ticket="+ticket);
            Set<String> set =(Set<String>)session.getAttribute(ticket);
            set.remove(url);
            session.invalidate();
        }else {
            throw new BaseException("用户登出异常");
        }
        return "redirect:"+backUrl;
    }



}
