package com.hu4java.common.config;

import com.hu4java.common.manager.QiniuStorageManager;
import com.hu4java.common.manager.StorageManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenzhenhu
 */
@Configuration
@EnableConfigurationProperties(value = {StorageProperties.class})
public class ApplicationConfiguration {



    @Bean
    @ConditionalOnProperty(value = "app.storage.type")
    public StorageManager storageManager(StorageProperties properties) {
        switch (properties.getType()) {
            case QINIU:
                return new QiniuStorageManager(properties);
            case ALIYUN:
            case QCLOUD:
            default:
                throw new RuntimeException("Not Found Implemented");
        }
    }

}
