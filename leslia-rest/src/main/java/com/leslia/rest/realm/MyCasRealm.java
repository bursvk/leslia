package com.leslia.rest.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class MyCasRealm extends CasRealm {

    private Logger logger= LoggerFactory.getLogger(MyCasRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();
        logger.info("获取 [角色][权限] username:"+username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> set=new HashSet<>();
        set.add(username);
        authorizationInfo.setRoles(set);
        authorizationInfo.setStringPermissions(set);
        Session session= SecurityUtils.getSubject().getSession();
        session.setAttribute("username",username);
        return authorizationInfo;
    }

}
