package com.leslia.sso.client.shiro.session;

import com.leslia.ware.redis.RedisUtil;
import com.leslia.sso.client.util.SerializableUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 基于redis的sessionDao，缓存共享session
 */
public class ShiroSessionDao extends CachingSessionDAO {

    private static Logger logger= LoggerFactory.getLogger(ShiroSessionDao.class);

    private static final String SHIRO_SESSION_KEY="SHIRO_SESSIONS";

    @Resource
    private RedisUtil redisUtil;

    @Override
    protected void doUpdate(Session session) {
        logger.info("doUpdate sessionId:"+session.getId());
        Serializable sessionId = generateSessionId(session);
        redisUtil.hset(SHIRO_SESSION_KEY,sessionId+"",SerializableUtil.serialize(session));
    }

    @Override
    protected void doDelete(Session session) {
        logger.info("doDelete sessionId:"+session.getId());
        Serializable sessionId = generateSessionId(session);
        redisUtil.hdel(SHIRO_SESSION_KEY,sessionId+"");
    }

    @Override
    protected Serializable doCreate(Session session) {
        logger.info("doCreate sessionId:"+session.getId());
        Serializable sessionId = generateSessionId(session);
        redisUtil.hset(SHIRO_SESSION_KEY,sessionId+"",SerializableUtil.serialize(session));
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        logger.info("doReadSession serializable:"+serializable);
        String session=(String)redisUtil.hget(SHIRO_SESSION_KEY,serializable+"");
        return SerializableUtil.deserialize(session);
    }


}
