package com.leslia.sso.service.controller;

import com.leslia.redis.RedisUtil;
import com.leslia.user.pojo.User;
import com.leslia.util.data.RedisKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequestMapping("/sso")
public class SSOController{

    private Logger logger= LoggerFactory.getLogger(SSOController.class);

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("/index")
    public void index(HttpServletRequest request){
        HttpSession session=request.getSession();
        logger.info("sessionId:"+session.getId());
    }


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpServletRequest request,ModelMap modelMap){
        logger.info("用户登录校验......");
        String backUrl=request.getParameter("backUrl");
        logger.info("登录地址 backUrl:"+backUrl);
        HttpSession session=request.getSession();
        logger.info("sessionId:"+session.getId());
        if(session.getAttribute("isLogin")==null) {
            session.setAttribute("backUrl",backUrl);
            return "login";
        }
        String token=(String) session.getAttribute("token");
        String userId=(String)session.getAttribute("userId");
        backUrl += "?token="+token+"&userId="+userId;
        return "redirect:"+backUrl;
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request, @RequestParam String username,@RequestParam String password){
        logger.info("用户登录 创建令牌 发送令牌......");
        if(username==null||username.equals("")){
            return "login";
        }
        if(password==null||password.equals("")){
            return "login";
        }
        long userId=redisUtil.incr(RedisKey.userId,1);
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setUserId(userId);
        redisUtil.hset(RedisKey.ssoLogin,userId+"",user);

        HttpSession session=request.getSession();
        logger.info("sessionId:"+session.getId());
        session.setAttribute("isLogin",true);
        session.setAttribute("userId",userId+"");
        String token = UUID.randomUUID().toString();
        logger.info("token:"+token);
        session.setAttribute("token",token);
        redisUtil.hset(RedisKey.ssoToken,userId+"",token);

        String backUrl=(String)session.getAttribute("backUrl");
        backUrl += "?token="+token+"&userId="+userId;
        return "redirect:"+backUrl;
    }



    @RequestMapping("/checkToken")
    @ResponseBody
    public String checkToken(@RequestParam String userId,@RequestParam String token){
        logger.info("校验令牌  地址登记......");
        String sso_token=(String)redisUtil.hget(RedisKey.ssoToken,userId);
        if(token.equals(sso_token)){
            return "success";
        }
        return "fail";
    }

    @RequestMapping("/logout")
    public String logOut(HttpServletRequest request){
        logger.info("摧毁全局会话......");
        HttpSession session=request.getSession();
        if(session.getAttribute("isLogin")==null){
            return "/login";
        }
        String userId=(String)session.getAttribute("userId");
        redisUtil.hdel(RedisKey.ssoToken,userId);
        session.invalidate();
        String backUrl=request.getParameter("backUrl");
        logger.info("backUrl"+backUrl);
        return "redirect:"+backUrl;
    }



}
