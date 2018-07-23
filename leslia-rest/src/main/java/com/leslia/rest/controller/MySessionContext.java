package com.leslia.rest.controller;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class MySessionContext {

    private static MySessionContext instance;
    public HashMap mymap;

    private MySessionContext() {
        mymap = new HashMap();
    }

    public static MySessionContext getInstance() {
        if (instance == null) {
            synchronized (MySessionContext.class){
                instance = new MySessionContext();
            }
        }
        return instance;
    }

    public  void AddSession(HttpSession session) {
        if (session != null) {
            mymap.put(session.getId(), session);
        }
    }

    public  void DelSession(HttpSession session) {
        if (session != null) {
            mymap.remove(session.getId());
        }
    }

    public HttpSession getSession(String session_id) {
        if (session_id == null) return null;
        return (HttpSession) mymap.get(session_id);
    }


}
