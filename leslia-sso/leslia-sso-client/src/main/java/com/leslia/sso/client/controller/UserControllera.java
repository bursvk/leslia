package com.leslia.sso.client.controller;

import com.leslia.api.pojo.ShiroUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sirUser")
public class UserControllera {

    private Logger logger= LoggerFactory.getLogger(UserControllera.class);

    @RequestMapping("/login")
    @ResponseBody
    public String login(String username,String password){
        logger.info("username:{},password:{}",username,password);
        try {
            Subject user= SecurityUtils.getSubject();
            if(user.isAuthenticated()){
               return "success_overtime";
            }
            UsernamePasswordToken token=new UsernamePasswordToken(username,password);
            token.setRememberMe(true);
            user.login(token);
        }catch ( UnknownAccountException uae ) {
            //账户不存在的操作
            logger.error("账户不存在");
            return "fail";
        } catch ( IncorrectCredentialsException ice ) {
            //密码不正确
            logger.error("密码不正确");
            return "fail";
        } catch ( LockedAccountException lae ) {
            //用户被锁定了
            logger.error("用户被锁定了");
            return "fail";
        } catch ( AuthenticationException ae ) {
            //无法判断的情形
            ae.printStackTrace();
            logger.error("账户认证异常");
            return "fail";
        } catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }


    @RequestMapping("/logout")
    @ResponseBody
    public String logout(){
        try{
            Subject user= SecurityUtils.getSubject();
            user.logout();
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "success";
    }


    @RequestMapping("/topic")
    @ResponseBody
    public void getTopic(){
        System.out.println(SecurityUtils.getSubject().getPrincipal());
        System.out.println(SecurityUtils.getSubject().isAuthenticated());
        if(SecurityUtils.getSubject().getPrincipal()!=null){
            ShiroUser user=(ShiroUser) SecurityUtils.getSubject().getPrincipal();
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
        }
    }




}
