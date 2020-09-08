package com.hu4java.common.config;

import com.hu4java.base.enums.StorageType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author chenzhenhu
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "app.storage")
public class StorageProperties {

    /** 存储类型*/
    private StorageType type;
    /** 域名*/
    private String domain;
    /** 访问密钥 access key*/
    private String ak;
    /** 私密密钥 secret key*/
    private String sk;
    /** 空间名*/
    private String bucket;
    /** token 过期时间(秒)*/
    private long expireSeconds = 3600;
}
