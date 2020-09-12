package com.hu4java.aop;

import com.hu4java.base.annotation.Log;
import com.hu4java.system.entity.OperationLog;
import com.hu4java.system.entity.User;
import com.hu4java.system.service.OperationLogService;
import com.hu4java.util.GsonUtils;
import com.hu4java.util.IPUtils;
import com.hu4java.util.ShiroUtils;
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

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 日志
 * @author hu4java
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    private ThreadLocal<OperationLog> threadLocal = new ThreadLocal<>();

    @Autowired
    private OperationLogService operationLogService;

    @Pointcut("@annotation(com.hu4java.base.annotation.Log)")
    public void pointcut() {
    }

    @Before(value = "pointcut()")
    public void before(JoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();

        Log log = method.getDeclaredAnnotation(Log.class);
        OperationLog operationLog = new OperationLog();
        operationLog.setOperateTime(LocalDateTime.now());
        operationLog.setParameter(GsonUtils.toJson(point.getArgs()));
        operationLog.setType(log.type().getType());
        operationLog.setDescription(log.desc());
        operationLog.setMethod(method.getName());

        threadLocal.set(operationLog);

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
            OperationLog operationLog = threadLocal.get();
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
                String ip = IPUtils.ip(servletRequestAttributes.getRequest());
                operationLog.setIp(ip);
            }

            saveLog(operationLog);

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            threadLocal.remove();
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
