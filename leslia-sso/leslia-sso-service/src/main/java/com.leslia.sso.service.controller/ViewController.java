package com.leslia.sso.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController{

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/logOutRedirect")
    public String logOutRedirect(){
        return "logOutRedirect";
    }



}
