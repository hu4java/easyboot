package com.huuu.init;

import com.huuu.system.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author huuu
 */
@Slf4j
@Component
public class InitDataRunner implements ApplicationRunner {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RegionService regionService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("加载数据");

        log.info("加载数据完成");
    }
}
