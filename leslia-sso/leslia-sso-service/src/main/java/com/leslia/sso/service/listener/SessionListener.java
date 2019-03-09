package com.leslia.sso.service.listener;

import com.leslia.util.http.HttpRequest;
import com.leslia.util.http.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Set;

public class SessionListener implements HttpSessionListener  {

    private static Logger logger= LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session=httpSessionEvent.getSession();
        logger.info("sessionId:{}",session.getId());
        String ticket=(String) session.getAttribute("ticket");
        Set<String> urls=(Set<String>) session.getAttribute(ticket);
        logger.info("客户端登出 urls:{}",urls);
        for(String url:urls){
            new Thread(()->HttpRequest.sendPost(url,"ticket="+ticket)).start();
        }
        SessionUtil.remove(session);
    }


}
