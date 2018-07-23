package com.leslia.rest.controller;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {


    private   MySessionContext myc=MySessionContext.getInstance();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("session 创建");
        myc.AddSession(httpSessionEvent.getSession());
        System.out.println("session size:"+myc.mymap.size());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("session 销毁");
        HttpSession session = httpSessionEvent.getSession();
        myc.DelSession(session);
        System.out.println("session size:"+myc.mymap.size());
    }

}
