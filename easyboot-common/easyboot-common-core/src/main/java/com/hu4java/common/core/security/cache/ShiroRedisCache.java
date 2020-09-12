package com.hu4java.common.core.security.cache;

import com.hu4java.common.core.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Shiro Redis 缓存
 * @author chenzhenhu
 */
@Slf4j
public class ShiroRedisCache<K, V> implements Cache<K, V> {

    private final String name;
    private final RedisTemplate redisTemplate;
    private final String prefix = RedisConstant.SHIRO_AUTHORITY_KEY_PREFIX;
    private final String principalIdFieldName = ShiroRedisCacheManager.DEFAULT_PRINCIPAL_ID_FIELD_NAME;
    private final long expire = 1800;

    public ShiroRedisCache(String name, RedisTemplate redisTemplate) {
        if (name == null) {
            throw new IllegalArgumentException("Cache name cannot be null.");
        } else if (redisTemplate == null) {
            throw new IllegalArgumentException("RedisTemplate cannot be null.");
        } else {
            this.name = name;
            this.redisTemplate = redisTemplate;
        }
    }

    @Override
    public V get(K k) throws CacheException {
        return (V) redisTemplate.opsForValue().get(getKey(k));
    }

    @Override
    public V put(K k, V v) throws CacheException {
        redisTemplate.opsForValue().set(getKey(k), v, expire, TimeUnit.SECONDS);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        V v = (V) redisTemplate.opsForValue().get(getKey(k));
        redisTemplate.delete(getKey(k));
        return v;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.delete(keys());
    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        return redisTemplate.keys(prefix + "*");
    }

    @Override
    public Collection<V> values() {
        return redisTemplate.opsForValue().multiGet(keys());
    }

    private String getKey(K k) {
        if (k instanceof PrincipalCollection) {
            String id = getPrincipalId(k);
            if (null != id) {
                return prefix + id;
            }
        }
        return prefix + k.toString();
    }


    private String getPrincipalId(K k) {
        PrincipalCollection principalCollection = (PrincipalCollection) k;
        try {
            Object principal = principalCollection.getPrimaryPrincipal();
            Class clazz = principal.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                if (field.getName().equals(principalIdFieldName)) {
                    field.setAccessible(true);
                    return prefix + field.get(principal).toString();
                }
            }

            Field field = clazz.getSuperclass().getDeclaredField(principalIdFieldName);
            field.setAccessible(true);
            String id = field.get(principal).toString();
            return id;
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
        } catch (NoSuchFieldException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
