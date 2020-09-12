package com.hu4java.common.core.config;

import com.hu4java.common.core.security.cache.ShiroRedisCacheManager;
import com.hu4java.common.core.security.cache.serializer.SessionSerializer;
import com.hu4java.common.core.security.session.ShiroRedisSessionDao;
import com.hu4java.common.core.security.session.ShiroRedisSessionManager;
import com.hu4java.common.core.filter.MyFormAuthenticationFilter;
import com.hu4java.util.ShiroUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro 配置
 * @author chenzhenhu
 */
public class ShiroConfiguration {


    @Bean(name = "sessionRedisTemplate")
    public RedisTemplate<String, Session> sessionRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Session> sessionRedisTemplate = new RedisTemplate<>();
        sessionRedisTemplate.setConnectionFactory(redisConnectionFactory);
        sessionRedisTemplate.setKeySerializer(new StringRedisSerializer());
        sessionRedisTemplate.setValueSerializer(new SessionSerializer());
        return sessionRedisTemplate;
    }

    @Bean
    public ShiroRedisCacheManager shiroRedisCacheManager(RedisTemplate redisTemplate) {
        return new ShiroRedisCacheManager(redisTemplate);
    }

    @Bean
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher(ShiroUtils.ALGORITHM_NAME);
        credentialsMatcher.setHashIterations(ShiroUtils.MAX_ITERATIONS);
        credentialsMatcher.setStoredCredentialsHexEncoded(ShiroUtils.USE_HEX);
        return credentialsMatcher;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager,
                                                         ShiroFilterChainDefinition shiroFilterChainDefinition) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setLoginUrl("/needLogin");
        filterFactoryBean.setUnauthorizedUrl("/unauthorized");

        filterFactoryBean.getFilters().put("authc", new MyFormAuthenticationFilter());


        Map<String, String> filterChainMap = new LinkedHashMap<>();

        filterChainMap.putAll(shiroFilterChainDefinition.getFilterChainMap());

        filterFactoryBean.setFilterChainDefinitionMap(filterChainMap);

        return filterFactoryBean;
    }

    @Bean
    public ShiroRedisSessionDao redisSessionDao(RedisTemplate<String, Session> sessionRedisTemplate) {
        ShiroRedisSessionDao shiroRedisSessionDao = new ShiroRedisSessionDao(sessionRedisTemplate);
        shiroRedisSessionDao.setSessionIdGenerator(new JavaUuidSessionIdGenerator());
        return shiroRedisSessionDao;
    }

    @Bean
    public ShiroRedisSessionManager sessionManager(ShiroRedisSessionDao redisSessionDao) {
        ShiroRedisSessionManager sessionManager = new ShiroRedisSessionManager();
        sessionManager.setSessionDAO(redisSessionDao);
        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000L); // 1小时
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(Realm realm, CacheManager cacheManager,
                                                     ShiroRedisSessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(realm);
        securityManager.setCacheManager(cacheManager);
        securityManager.setSessionManager(sessionManager);

        return securityManager;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
