package com.leslia.sso.client.filter;

import com.leslia.sso.client.conf.Resources;
import com.leslia.util.exception.BaseException;
import com.leslia.util.http.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutFilter implements Filter {

    private  Logger logger= LoggerFactory.getLogger(LogoutFilter.class);

    private  String sso_service_logout_url=Resources.SSO_SERVICE_LOGOUT_URL;

    private  String sso_client_logout_url=Resources.SSO_CLIENT_LOGOUT_URL;

    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String ticket=request.getParameter("ticket");
        if(ticket!=null&&!ticket.equals("")){
            ticket=ticket.replace(' ','+');
            HttpSession session= SessionUtil.get(ticket);
            if(session !=null&&(Boolean) session.getAttribute("isLogin")){
                String username=(String)session.getAttribute("username");
                session.invalidate();
                logger.info("用户登出成功 username:{}",username);
            }
            return;
        }

        HttpSession session=request.getSession();
        logger.info("sessionId:{}",session.getId());
        if(session!=null&&(Boolean) session.getAttribute("isLogin")){
            logger.info("向用户中心发出登出请求");
            ticket=(String) session.getAttribute("ticket");
            String retUrl = request.getHeader("Referer");
            String sendUrl=String.format(sso_service_logout_url+"?ticket=%s&url=%s&backUrl=%s",ticket,sso_client_logout_url,retUrl);
            response.sendRedirect(sendUrl);
            return;
        }else{
            throw new BaseException("未找到用户信息");
        }
    }

    @Override
    public void destroy() {

    }

}
