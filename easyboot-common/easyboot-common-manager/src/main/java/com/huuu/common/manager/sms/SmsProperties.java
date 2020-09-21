package com.huuu.common.manager.sms;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author huuu
 */
@Getter
@Setter
@ConfigurationProperties("app.sms")
public class SmsProperties {

    /** 是否启用*/
    private boolean enable = false;
}
