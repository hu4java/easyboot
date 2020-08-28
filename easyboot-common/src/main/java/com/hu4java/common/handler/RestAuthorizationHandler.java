package com.hu4java.common.handler;

import com.hu4java.common.result.Result;
import com.hu4java.common.result.ResultCode;
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
