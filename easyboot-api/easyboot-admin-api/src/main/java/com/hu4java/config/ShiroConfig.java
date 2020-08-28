package com.hu4java.config;

import com.hu4java.common.config.ShiroConfiguration;
import com.hu4java.sercuity.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro 配置
 * @author chenzhenhu
 */
@Configuration
public class ShiroConfig extends ShiroConfiguration {

    @Bean
    public UserRealm realm(CacheManager cacheManager, HashedCredentialsMatcher credentialsMatcher) {
        UserRealm realm = new UserRealm();
        realm.setCredentialsMatcher(credentialsMatcher);
        realm.setCachingEnabled(true);
        realm.setCacheManager(cacheManager);
        realm.setAuthorizationCachingEnabled(true);
        return realm;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        chainDefinition.addPathDefinition("/login", "anon");
        chainDefinition.addPathDefinition("/test", "anon");
        chainDefinition.addPathDefinition("/**", "authc");


        return chainDefinition;
    }
}
