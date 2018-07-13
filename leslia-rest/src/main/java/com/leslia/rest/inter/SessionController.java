package com.leslia.rest.inter;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/session")
public class SessionController {

    @RequestMapping("/view")
    @ResponseBody
    public String view(HttpServletRequest request){
        String sessionId=request.getSession().getId();
        return sessionId;
    }

}
