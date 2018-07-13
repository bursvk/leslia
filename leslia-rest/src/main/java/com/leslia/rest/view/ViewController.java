package com.leslia.rest.view;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/success")
    public String success(){
        return "success";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }

    @RequestMapping("/user")
    public String user(){
        return "user";
    }

    @RequestMapping("/admin")
    public String admin(){
        Subject user= SecurityUtils.getSubject();
        System.out.println(user.hasRole("admin"));
        return "admin";
    }


}
