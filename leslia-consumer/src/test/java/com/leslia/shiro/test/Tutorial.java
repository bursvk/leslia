package com.leslia.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tutorial {

    private Logger logger= LoggerFactory.getLogger(Tutorial.class);

    @Test
    public void test(){
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro.ini");
        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject currentUser = SecurityUtils.getSubject();
        Session session=currentUser.getSession();
        session.setAttribute("key1","val1");

        if(!currentUser.isAuthenticated()){
            UsernamePasswordToken token=new UsernamePasswordToken("aihe","aihe");
            token.setRememberMe(true);
            try{
                currentUser.login(token);
                //当我们获登录用户之后
                logger.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");
                if(currentUser.hasRole("client")){
                    logger.info("Look is in your role" );
                }else{
                    logger.info("Sorry ......");
                }

                // 查看用户是否有某个权限
                if ( currentUser.isPermitted( "look:desk" ) ) {
                    logger.info("You can look.  Use it wisely.");
                } else {
                    logger.info("Sorry, you can't look.");
                }

                if ( currentUser.isPermitted( "winnebago:drive:eagle5" ) ) {
                    logger.info("You are permitted to 'drive' the 'winnebago' with license plate (id) 'eagle5'.  " +
                            "Here are the keys - have fun!");
                } else {
                    logger.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
                }
                //登出

                currentUser.logout();


            }catch ( UnknownAccountException uae ) {
                //账户不存在的操作
                logger.info("账户不存在");
            } catch ( IncorrectCredentialsException ice ) {
                //密码不正确
                logger.info("密码不正确");
            } catch ( LockedAccountException lae ) {
                //用户被锁定了
                logger.info("用户被锁定了");
            } catch ( AuthenticationException ae ) {
                //无法判断的情形
                logger.info("无法判断的情形");
            }

        }


    }

}
