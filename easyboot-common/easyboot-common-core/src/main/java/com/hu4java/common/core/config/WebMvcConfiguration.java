package com.hu4java.common.core.config;

import com.hu4java.common.core.constant.DateConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;

/**
 * Web Mvc 配置
 * @author hu4java
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("*")
                .allowedMethods("*")
                .maxAge(3600);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setDateFormatter(DateTimeFormatter.ofPattern(DateConstants.DEFAULT_DATE_FORMAT));
        registrar.setTimeFormatter(DateTimeFormatter.ofPattern(DateConstants.DEFAULT_TIME_FORMAT));
        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern(DateConstants.DEFAULT_DATE_TIME_FORMAT));
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(registry);
    }
}
