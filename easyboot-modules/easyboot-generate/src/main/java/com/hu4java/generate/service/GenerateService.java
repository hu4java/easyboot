package com.hu4java.generate.service;

import com.hu4java.generate.request.GenerateRequest;

import java.util.zip.ZipOutputStream;

/**
 * @author hu4java
 */
public interface GenerateService {

    /**
     * 生成代码
     * @param request
     * @param zipOutputStream
     */
    void generate(GenerateRequest request, ZipOutputStream zipOutputStream) throws Exception;
}
