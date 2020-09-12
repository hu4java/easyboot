package com.hu4java.web.common.controller;

import com.hu4java.base.annotation.NoAuth;
import com.hu4java.base.result.Result;
import com.hu4java.base.result.ResultCode;
import com.hu4java.web.common.request.LoginRequest;
import com.hu4java.web.common.response.TokenResponse;
import com.hu4java.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * 公共分类
 * 登录，登出
 * @author hu4java
 */
@RestController
public class LoginController {

    /**
     * 登录
     * @param request   登录数据
     * @return          Token
     */
    @NoAuth
    @PostMapping("/login")
    public Result<TokenResponse> login(@RequestBody @Validated LoginRequest request) {
        UsernamePasswordToken token = new UsernamePasswordToken(request.getUsername(), request.getPassword());
        Subject subject = SecurityUtils.getSubject();

        ResultCode resultCode = ResultCode.USERNAME_OR_PASSWORD_ERROR;
        try {

            subject.login(token);

            if (subject.isAuthenticated()) {
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
}
