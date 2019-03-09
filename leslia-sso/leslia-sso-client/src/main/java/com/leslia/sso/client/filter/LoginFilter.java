package com.leslia.sso.client.filter;

import com.leslia.sso.client.conf.Resources;
import com.leslia.util.exception.BaseException;
import com.leslia.util.http.HttpRequest;
import com.leslia.util.http.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    private Logger logger= LoggerFactory.getLogger(LoginFilter.class);

    private String sso_service_login_url= Resources.SSO_SERVICE_LOGIN_URL;

    private String sso_service_verify_ticket_url=Resources.SSO_SERVICE_VERIFY_TICKET_URL;

    private String sso_client_logout_url=Resources.SSO_CLIENT_LOGOUT_URL;


    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        HttpSession session=request.getSession();
        String ticket = request.getParameter("ticket");
        if(ticket!=null&&!ticket.equals("")) {
            ticket=ticket.replace(' ','+');
            String params="ticket="+ticket+"&url="+sso_client_logout_url;
            String username = HttpRequest.sendPost(sso_service_verify_ticket_url, params);
            if (username != null && !username.equals("")) {
                session.setAttribute("isLogin", true);
                session.setAttribute("username", username);
                session.setAttribute("ticket",ticket);
                SessionUtil.add(ticket,session);
                filterChain.doFilter(servletRequest, servletResponse);
                logger.info("用户登录成功 username:{}",username);
                return;
            }else {
                throw new BaseException("ticket 验证异常");
            }
        }

        if(session.getAttribute("isLogin")!=null&&(Boolean) session.getAttribute("isLogin")){
            logger.info("用户已经登录 username:{}",session.getAttribute("username"));
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        logger.info("用户未登录 向用户中心发起登录请求");
        String backUrl= request.getRequestURL().toString();
        response.sendRedirect(sso_service_login_url+"?backUrl="+backUrl);
    }

    @Override
    public void destroy() {
    }


}
