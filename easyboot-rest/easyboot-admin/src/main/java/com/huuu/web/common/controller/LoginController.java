package com.huuu.web.common.controller;

import com.huuu.base.annotation.NoAuth;
import com.huuu.base.result.Result;
import com.huuu.base.result.ResultCode;
import com.huuu.common.core.constant.Constants;
import com.huuu.system.entity.LoginLog;
import com.huuu.system.service.LoginLogService;
import com.huuu.util.IPUtils;
import com.huuu.util.ShiroUtils;
import com.huuu.web.common.request.LoginRequest;
import com.huuu.web.common.response.TokenResponse;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 公共分类
 * 登录，登出
 * @author huuu
 */
@RestController
public class LoginController {

    @Autowired
    private LoginLogService loginLogService;

    /**
     * 登录
     * @param request   登录数据
     * @return          Token
     */
    @NoAuth
    @PostMapping("/login")
    public Result<TokenResponse> login(@RequestBody @Validated LoginRequest request, HttpServletRequest httpServletRequest) {
        UsernamePasswordToken token = new UsernamePasswordToken(request.getUsername(), request.getPassword());
        Subject subject = SecurityUtils.getSubject();

        ResultCode resultCode = ResultCode.USERNAME_OR_PASSWORD_ERROR;
        try {

            subject.login(token);

            if (subject.isAuthenticated()) {
                saveLog(httpServletRequest, request, ResultCode.SUCCESS);
                Serializable sessionId = subject.getSession().getId();
                TokenResponse response = new TokenResponse();
                response.setToken(sessionId.toString());
                return Result.success(response);
            }
        } catch (UnknownAccountException e) {
            resultCode = ResultCode.UNKNOWN_ACCOUNT;
        } catch (AuthenticationException e) {
            resultCode = ResultCode.USERNAME_OR_PASSWORD_ERROR;
        }
        saveLog(httpServletRequest, request, resultCode);
        return Result.error(resultCode);
    }

    /**
     * 登出
     * @return
     */
    @RequestMapping("/logout")
    public Result<Void> logout() {
        ShiroUtils.logout();
        return Result.success();
    }

    private void saveLog(HttpServletRequest request, LoginRequest data, ResultCode resultCode) {
        String ip = IPUtils.ip(request);
        String userAgent = request.getHeader(Constants.USER_AGENT);
        Browser browser = UserAgent.parseUserAgentString(userAgent).getBrowser();
        OperatingSystem operatingSystem = UserAgent.parseUserAgentString(userAgent).getOperatingSystem();

        LoginLog log = new LoginLog();
        log.setAccount(data.getUsername());
        log.setIp(ip);
        log.setBrowser(browser.getName());
        log.setOs(operatingSystem.getName());
        log.setErrorMsg(resultCode.getMessage());
        log.setLoginTime(LocalDateTime.now());
        log.setStatus(ResultCode.SUCCESS == resultCode ? 0 : 1);

        loginLogService.save(log);
    }
}
