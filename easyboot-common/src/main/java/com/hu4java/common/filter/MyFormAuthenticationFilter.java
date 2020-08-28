package com.hu4java.common.filter;

import com.hu4java.common.security.session.ShiroRedisSessionManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author hu4java
 */
@Slf4j
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String token = WebUtils.toHttp(request).getHeader(ShiroRedisSessionManager.TOKEN_HEADER);
        if (StringUtils.isBlank(token)) {
            return false;
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

}
