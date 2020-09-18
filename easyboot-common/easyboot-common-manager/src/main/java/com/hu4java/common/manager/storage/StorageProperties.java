package com.hu4java.common.manager.storage;

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

    /** 是否启用*/
    private boolean enable = false;
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
    /** 阿里云 endpoint*/
    private String endpoint;
    /** token 过期时间(秒)*/
    private long expireSeconds = 3600;
    /** 本地存储目录*/
    private String dir;
}
