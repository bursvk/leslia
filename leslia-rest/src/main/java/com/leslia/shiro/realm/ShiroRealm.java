package com.leslia.shiro.realm;
import com.leslia.user.api.ShiroUserService;
import com.leslia.user.pojo.ShiroUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ShiroRealm extends AuthorizingRealm {

    private Logger logger= LoggerFactory.getLogger(ShiroRealm.class);

    private ShiroUserService shiroUserService;

    private static final String login="user_sessionId";


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("用户授权......");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        try{
            ShiroUser user=(ShiroUser) SecurityUtils.getSubject().getPrincipal();
            if(user==null){
                return null;
            }
            info.addRole(user.getRole());
            logger.info("用户获得的权限是"+info.getRoles());
        }catch (Exception e){
            e.printStackTrace();
            logger.error("用户授权异常");
        }
        return info;
    }


    /**
     * 认证 MD5+盐值
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("用户认证......");
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        String username=token.getUsername();
        ShiroUser user=shiroUserService.getShiroUserByName(username);
        if(user==null){
            throw new UnknownAccountException("用户不存在");
        }
        ByteSource salt = ByteSource.Util.bytes(username);
        return new SimpleAuthenticationInfo(user,user.getPassword(),salt,getName());
    }


    public static void main(String[] args) throws Exception {
            try {
                if(1==1){
                    throw new Exception("运行异常");
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        String hashAlgorithmName = "MD5";//加密方式
        Object crdentials = "123456";//密码原值
        ByteSource salt = ByteSource.Util.bytes("user");//盐值
        int hashIterations = 1024;//加密1024次
        Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
        System.out.println(result.toString());
    }




    public ShiroUserService getShiroUserService() {
        return shiroUserService;
    }

    public void setShiroUserService(ShiroUserService shiroUserService) {
        this.shiroUserService = shiroUserService;
    }


}
