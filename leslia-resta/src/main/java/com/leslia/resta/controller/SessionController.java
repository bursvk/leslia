package com.leslia.resta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/session")
public class SessionController {

    @RequestMapping("/view")
    @ResponseBody
    public String view(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            String cookidName=cookie.getName();
            if(cookidName.equals("JSESSIONID")){
                String sessionId=cookie.getValue();
                System.out.println("JSESSIONID:"+sessionId);
            }
        }

        HttpSession session=request.getSession();
        String sessionId=session.getId();
        System.out.println("sessionId:"+sessionId);
        return sessionId;
    }

    @RequestMapping("/cookie")
    @ResponseBody
    public void cookie(HttpServletResponse response){
        Cookie cookie1=new Cookie("username","leslia");
        Cookie cookie2=new Cookie("password","123456");
        response.addCookie(cookie1);
        response.addCookie(cookie2);
    }



    @RequestMapping("/check/cookie")
    //@ResponseBody
    public String checkCookie(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies=request.getCookies();
        Cookie cookie1=null;
        Cookie cookie2=null;
        for(Cookie cookie:cookies){
            System.out.println(cookie.getName()+":"+cookie.getValue());
            if(cookie.getName().equals("username")){
                cookie1=cookie;
            }
            if(cookie.getName().equals("password")){
                cookie2=cookie;
            }
        }
        if(cookie1!=null&&cookie2!=null){
            if(cookie1.getValue().equals("leslia")&&cookie2.getValue().equals("123456")){
                System.out.println("登录成功");
            }
        }

    /*    try {
            System.out.println("重定向");
            System.out.println(request.getRequestURI() + "?" + System.currentTimeMillis());
            response.sendRedirect(request.getRequestURI() + "?" + "time="+System.currentTimeMillis());
        }catch (IOException e){
            e.printStackTrace();
        }
*/
        return "index";

    }


    @RequestMapping("/getSession")
    @ResponseBody
    public void getSession(HttpServletRequest request){
        HttpSession session=request.getSession();
        String account=session.getAttribute("account")+"";
        System.out.println(account);
    }

    @RequestMapping("/session1")
    @ResponseBody
    public void session1(HttpServletRequest request){
        Cookie[] cookiess=request.getCookies();
        for(Cookie cookie:cookiess){
            String cookidName=cookie.getName();
            if(cookidName.equals("JSESSIONID")){
                String sessionId=cookie.getValue();
                System.out.println("JSESSIONID:"+sessionId);
            }
        }
        HttpSession session=request.getSession();
        session.invalidate();
    }


    @RequestMapping("/url1")
    //@ResponseBody
    public String url1(HttpServletRequest request,HttpServletResponse response) {
        String sessionId = request.getSession().getId();
        System.out.println("sessionId1:" + sessionId);
     /*   try {
            System.out.println(response.encodeRedirectURL("/session/url2"));
            response.sendRedirect(response.encodeRedirectURL("/session/url2"));
        }catch (IOException e){
            e.printStackTrace();
        }*/
        //System.out.println(response.encodeURL("/session/url1"));
        return "index";
    }
    @RequestMapping("/url2")
    @ResponseBody
    public void url2(HttpServletRequest request,HttpServletResponse response){
       String sessionId= request.getSession().getId();
       System.out.println("sessionId2:"+sessionId);
    }




}
