package com.huuu.generate.service;

import com.huuu.generate.request.GenerateRequest;

import java.util.zip.ZipOutputStream;

/**
 * @author huuu
 */
public interface GenerateService {

    /**
     * 生成代码
     * @param request
     * @param zipOutputStream
     */
    void generate(GenerateRequest request, ZipOutputStream zipOutputStream) throws Exception;
}
