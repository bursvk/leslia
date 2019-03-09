package com.leslia.util.http;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionUtil {


    private static Map<String, HttpSession> sessionMap=new HashMap();

    public static void add(HttpSession session){
        sessionMap.put(session.getId(),session);
    }

    public static void add(String sessionId,HttpSession session){
        sessionMap.put(sessionId,session);
    }

    public static void remove(HttpSession session){
        sessionMap.remove(session.getId());
    }

    public static void remove(String sessionId){
        sessionMap.remove(sessionId);
    }

    public static HttpSession get(String sessionId){
        return sessionMap.get(sessionId);
    }


}
