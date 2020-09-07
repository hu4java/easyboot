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

    private StorageType type;

    private String ak;

    private String sk;

    private String bucket;
}
