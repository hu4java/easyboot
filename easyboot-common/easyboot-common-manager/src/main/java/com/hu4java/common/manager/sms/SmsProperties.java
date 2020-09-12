package com.hu4java.common.manager.sms;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hu4java
 */
@Getter
@Setter
@ConfigurationProperties("app.sms")
public class SmsProperties {

    /** 是否启用*/
    private boolean enable = false;
}
