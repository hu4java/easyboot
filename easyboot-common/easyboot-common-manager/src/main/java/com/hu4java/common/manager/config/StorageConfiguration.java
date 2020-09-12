package com.hu4java.common.manager.config;

import com.hu4java.common.manager.storage.QiniuStorageManager;
import com.hu4java.common.manager.storage.StorageManager;
import com.hu4java.common.manager.storage.StorageProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hu4java
 */
@Configuration
@ConditionalOnProperty(value = "app.storage.enable", havingValue = "true")
@EnableConfigurationProperties(value = {StorageProperties.class})
public class StorageConfiguration {


    @Bean
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
