package com.hu4java.common.core.handler;

import com.hu4java.base.result.Result;
import com.hu4java.base.result.ResultCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hu4java
 */
@RestController
public class RestAuthorizationHandler {

    @RequestMapping("/needLogin")
    public Result<Void> needLogin() {
        return Result.error(ResultCode.NEED_LOGIN);
    }

    @RequestMapping("/unauthorized")
    public Result<Void> unauthorized() {
        return Result.error(ResultCode.UNAUTHORIZED);
    }
}
