package com.leslia.sso.client.listener;

import com.leslia.util.http.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    private static Logger logger= LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session=httpSessionEvent.getSession();
        if(session.getAttribute("isLogin")!=null&&(Boolean)session.getAttribute("isLogin")){
            logger.info("sessionId:{}",session.getId());
            String ticket=(String) session.getAttribute("ticket");
            SessionUtil.remove(ticket);
        }
    }


}
