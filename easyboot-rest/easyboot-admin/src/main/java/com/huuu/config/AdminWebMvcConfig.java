package com.huuu.config;

import com.huuu.interceptor.PreviewInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chenzhenhu
 */
@Configuration
public class AdminWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private PreviewInterceptor previewInterceptor;
    @Autowired
    private Environment environment;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (environment.getProperty("spring.profiles.active").equals("preview")) {
            registry.addInterceptor(previewInterceptor).addPathPatterns(
                    "/**/save",
                    "/**/update*",
                    "/**/remove",
                    "/**/resetPassword"
            );
        }

    }
}
