package com.huuu.interceptor;

import com.huuu.base.result.Result;
import com.huuu.base.result.ResultCode;
import com.huuu.util.GsonUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenzhenhu
 */
@Component
public class PreviewInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Result<Object> result = Result.error(ResultCode.ERROR.getCode(), "演示模式不允许操作");
        response.reset();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(GsonUtils.toJson(result));
        return false;
    }
}
