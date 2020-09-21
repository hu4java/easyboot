package com.huuu.common.core.security.cache;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author chenzhenhu
 */
public class ShiroRedisCacheManager extends AbstractCacheManager {

    public static final String DEFAULT_PRINCIPAL_ID_FIELD_NAME = "id";

    private RedisTemplate redisTemplate;

    public ShiroRedisCacheManager(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected Cache createCache(String name) throws CacheException {
        return new ShiroRedisCache(name, redisTemplate);
    }
}
