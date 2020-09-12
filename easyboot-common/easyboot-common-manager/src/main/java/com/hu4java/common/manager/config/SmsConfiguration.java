package com.hu4java.common.manager.config;

import com.hu4java.common.manager.sms.SmsProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author hu4java
 */
@Configuration
@ConditionalOnProperty(value = "app.sms.enable", havingValue = "true")
@EnableConfigurationProperties(value = { SmsProperties.class })
public class SmsConfiguration {
}
