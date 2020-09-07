package com.hu4java.common.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenzhenhu
 */
@Configuration
@EnableConfigurationProperties(value = {StorageProperties.class})
public class ApplicationConfiguration {


}
