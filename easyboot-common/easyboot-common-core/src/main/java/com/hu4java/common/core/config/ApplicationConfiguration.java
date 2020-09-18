package com.hu4java.common.core.config;

import com.hu4java.common.manager.sms.SmsManger;
import com.hu4java.common.manager.sms.SmsProperties;
import com.hu4java.common.manager.storage.manager.LocalStorageManager;
import com.hu4java.common.manager.storage.manager.QiniuStorageManager;
import com.hu4java.common.manager.storage.StorageManager;
import com.hu4java.common.manager.storage.StorageProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenzhenhu
 */
@Configuration
public class ApplicationConfiguration {


    @Configuration
    @EnableConfigurationProperties(value = StorageProperties.class)
    @ConditionalOnProperty(value = "app.storage.enable", havingValue = "true")
    static class StorageConfiguration {

        @Bean
        public StorageManager storageManager(StorageProperties properties) {
            switch (properties.getType()) {
                case QINIU:
                    return new QiniuStorageManager(properties);
                case LOCAL:
                    return new LocalStorageManager(properties);
                case ALIYUN:
                case QCLOUD:
                default:
                    throw new RuntimeException("Not Found Implemented");
            }
        }
    }

    @EnableConfigurationProperties(value = SmsProperties.class)
    @ConditionalOnProperty(value = "app.sms.enable", havingValue = "true")
    static class SmsConfiguration {

        @Bean
        public SmsManger smsManger(SmsProperties smsProperties) {
            return null;
        }
    }
}
