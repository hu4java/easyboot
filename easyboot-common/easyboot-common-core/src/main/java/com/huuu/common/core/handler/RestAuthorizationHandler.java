package com.huuu.common.core.handler;

import com.huuu.base.result.Result;
import com.huuu.base.result.ResultCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huuu
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
