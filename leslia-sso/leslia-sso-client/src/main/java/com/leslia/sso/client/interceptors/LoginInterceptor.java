package com.leslia.sso.client.interceptors;

import com.leslia.sso.client.base.BaseConfig;
import com.leslia.util.exception.BaseException;
import com.leslia.util.http.HttpRequest;
import com.leslia.util.http.RequestUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {


    private Logger logger= LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            HttpSession session=request.getSession();
            logger.info("sessionId:"+session.getId());
            if(session.getAttribute("isLogin")==null){
                throw new BaseException(10001,"用户未登录");
            }
            String token=(String)session.getAttribute("token");
            String userId=(String) session.getAttribute("userId");
            String params=String.format("token=%s&userId=%s",token,userId);
            String result= HttpRequest.sendPost(BaseConfig.getSso_url()+"/sso/checkToken",params);
            logger.info("令牌校验结果result:"+result);
            if(result.equals("fail")){
                session.invalidate();
                throw new BaseException(10002,"令牌校验失败");
            }
            return true;
        }catch (BaseException e){
            logger.info(e.getErrorCode()+":"+e.getErrorMessage());
            if(e.getErrorCode()==10001||e.getErrorCode()==10002){
                String backUrl = request.getRequestURL().toString();
                backUrl= RequestUrl.baseUrl(backUrl);
                backUrl+="/user/receiveToken";
                logger.info("sso_client_url:"+backUrl);
                response.sendRedirect(BaseConfig.getSso_url()+"/sso/login?backUrl="+backUrl);
            }
            return false;
        }
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("登录拦截器2......");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("登录拦截器3.......");
    }


}
