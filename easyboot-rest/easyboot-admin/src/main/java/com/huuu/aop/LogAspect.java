package com.huuu.aop;

import com.huuu.base.annotation.Log;
import com.huuu.common.core.constant.Constants;
import com.huuu.system.entity.OperationLog;
import com.huuu.system.entity.User;
import com.huuu.system.service.OperationLogService;
import com.huuu.util.GsonUtils;
import com.huuu.util.IPUtils;
import com.huuu.util.ShiroUtils;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 日志
 * @author huuu
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    private ThreadLocal<Long> timeThreadLocal = new ThreadLocal<>();

    @Autowired
    private OperationLogService operationLogService;

    @Pointcut("@annotation(com.huuu.base.annotation.Log)")
    public void pointcut() {
    }

    @Before(value = "pointcut()")
    public void before() {
        timeThreadLocal.set(System.currentTimeMillis());
    }
    
    @AfterReturning(pointcut = "pointcut()", returning = "returnBody")
    public void afterReturning(JoinPoint point, Object returnBody) {
        handleLog(point, returnBody, null);
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "ex")
    public void afterThrowing(JoinPoint point, Exception ex) {
        handleLog(point, null, ex);
    }

    private void handleLog(JoinPoint point, Object returnObject, Exception ex) {
        try {

            MethodSignature methodSignature = (MethodSignature) point.getSignature();
            Method method = methodSignature.getMethod();

            Log log = method.getDeclaredAnnotation(Log.class);
            OperationLog operationLog = new OperationLog();
            operationLog.setOperateTime(LocalDateTime.now());
            operationLog.setParameter(GsonUtils.toJson(point.getArgs()));
            operationLog.setType(log.type().getType());
            operationLog.setDescription(log.desc());

            String className = point.getTarget().getClass().getName();
            operationLog.setMethod(className + "." + method.getName());

            if (null != returnObject) {
                operationLog.setStatus(0);
                operationLog.setReturnBody(GsonUtils.toJson(returnObject));
            }
            if (null != ex) {
                operationLog.setStatus(1);
                operationLog.setErrorMsg(ex.getMessage());
            }

            RequestAttributes requestAttributes =  RequestContextHolder.getRequestAttributes();
            if (null != requestAttributes) {
                ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
                HttpServletRequest request = servletRequestAttributes.getRequest();
                String ip = IPUtils.ip(request);
                operationLog.setIp(ip);
                operationLog.setRequestMethod(request.getMethod());
                operationLog.setRequestUrl(request.getRequestURI());

                String userAgent = request.getHeader(Constants.USER_AGENT);
                Browser browser = UserAgent.parseUserAgentString(userAgent).getBrowser();
                operationLog.setBrowser(browser.getName() + "/" + browser.getVersion(userAgent).getVersion());
            }

            Long startTime = timeThreadLocal.get();

            operationLog.setTime(System.currentTimeMillis() - startTime);

            saveLog(operationLog);

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            timeThreadLocal.remove();
        }
    }

    @Async
    public void saveLog(OperationLog log) {
        if (ShiroUtils.isLogin()) {
            User current = ShiroUtils.currentLogin();
            log.setOperateUserId(current.getId());
            log.setOperateUser(current.getName());
        }
        operationLogService.save(log);
    }
}
