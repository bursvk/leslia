package com.leslia.resta.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {

    private Logger logger= LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("过滤器 初始化.....");
        logger.info("filerName:{}",filterConfig.getFilterName());
        logger.info("name:{}",filterConfig.getInitParameter("name"));
        logger.info(filterConfig.getInitParameterNames()+"");
        logger.info(filterConfig.getServletContext()+"");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("过滤器");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("过滤器 销毁.....");
    }

}
