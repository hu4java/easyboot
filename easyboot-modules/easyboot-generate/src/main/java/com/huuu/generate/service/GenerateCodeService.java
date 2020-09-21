package com.huuu.generate.service;

import com.huuu.generate.request.GenerateRequest;

/**
 * @author chenzhenhu
 */
public interface GenerateCodeService {


    /**
     * 代码
     * @param request
     * @return
     */
    String generate(GenerateRequest request);

    /**
     * 路径
     * @param request
     * @return
     */
    String path(GenerateRequest request);
}
