package com.huuu.common.core.security.session;

import com.huuu.common.core.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis Session DAO
 * @author chenzhenhu
 */
@Slf4j
public class ShiroRedisSessionDao extends AbstractSessionDAO {

    /** redis 操作*/
    private RedisTemplate<String, Session> redisTemplate;

    public ShiroRedisSessionDao(RedisTemplate<String, Session> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (null == sessionId) {
            return null;
        }

        return redisTemplate.opsForValue().get(getKey(sessionId));
    }

    @Override
    public void update(Session session) throws UnknownSessionException {

        this.saveSession(session);
    }

    @Override
    public void delete(Session session) {

        if (null == session || null == session.getId()) {
            return;
        }
        redisTemplate.delete(getKey(session.getId()));
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<>();
        Set<String> keys = this.redisTemplate.keys(RedisConstant.SHIRO_SESSION_KEY_PREFIX + "*");
        if (keys != null && keys.size() > 0) {
            for (String key : keys) {
                Session session = this.redisTemplate.opsForValue().get(key);
                sessions.add(session);
            }
        }

        return sessions;
    }

    private void saveSession(Session session) throws UnknownSessionException {
        if (null == session || null == session.getId()) {
            return;
        }
        // 是否已登录
//        Object authenticatedAttribute = session.getAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY);
//        if (null == authenticatedAttribute) {
//
//            this.redisTemplate.opsForValue().set(getKey(session.getId()), session, 10, TimeUnit.SECONDS);
//            return;
//        }
//        boolean status = (Boolean) authenticatedAttribute;
//        if (status) {
//            this.redisTemplate.opsForValue().set(getKey(session.getId()), session, session.getTimeout(), TimeUnit.MILLISECONDS);
//        }
        this.redisTemplate.opsForValue().set(getKey(session.getId()), session, session.getTimeout(), TimeUnit.MILLISECONDS);
    }

    private String getKey(Serializable sessionId) {
        return RedisConstant.SHIRO_SESSION_KEY_PREFIX + sessionId.toString();
    }
}
