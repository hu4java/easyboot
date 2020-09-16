package com.hu4java.generate.service;

import com.hu4java.generate.request.GenerateRequest;

import java.util.zip.ZipOutputStream;

/**
 * @author hu4java
 */
public interface GenerateService {

    /**
     * 代码
     * @param request
     * @param outputStream
     * @return
     */
    void generate(GenerateRequest request, ZipOutputStream outputStream);
}
