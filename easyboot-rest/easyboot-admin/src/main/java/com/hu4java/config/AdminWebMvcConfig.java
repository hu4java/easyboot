package com.hu4java.config;

import com.hu4java.interceptor.PreviewInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chenzhenhu
 */
@Configuration
public class AdminWebMvcConfig implements WebMvcConfigurer {

    @Autowired(required = false)
    private PreviewInterceptor previewInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (null != previewInterceptor) {
            registry.addInterceptor(new PreviewInterceptor()).addPathPatterns(
                    "/**/save",
                    "/**/update*",
                    "/**/remove",
                    "/**/resetPassword"
            );
        }

    }
}
