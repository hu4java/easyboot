package com.hu4java.web.common.controller;

import com.hu4java.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenzhenhu
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @GetMapping("/qiniu/token")
    public Result<String> qiniuToken() {
        return Result.success(null);
    }

}
