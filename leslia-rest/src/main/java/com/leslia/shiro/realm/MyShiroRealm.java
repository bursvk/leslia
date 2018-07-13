package com.leslia.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;


public class MyShiroRealm extends AuthorizingRealm {

    private static final String USER_NAME = "leslia";
    private static final String PASSWORD = "123456";

    /*
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Set<String> roleNames = new HashSet<>();
        Set<String> permissions = new HashSet<String>();
        roleNames.add("admin1");//添加角色
        permissions.add("newPage.html");  //添加权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        info.setStringPermissions(permissions);
        return info;
    }

    /*
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if(token.getUsername().equals(USER_NAME)){
            System.out.println("验证用户名");
            return new SimpleAuthenticationInfo(USER_NAME, PASSWORD, getName());
        }else{
            System.out.println("用户名不存在");
            throw new AuthenticationException();
        }
    }




}
