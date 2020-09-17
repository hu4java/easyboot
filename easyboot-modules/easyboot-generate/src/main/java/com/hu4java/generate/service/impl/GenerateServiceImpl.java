package com.hu4java.generate.service.impl;

import com.hu4java.generate.request.GenerateRequest;
import com.hu4java.generate.service.GenerateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.zip.ZipOutputStream;

/**
 * 代码生成
 * @author hu4java
 */
@Slf4j
@Service
public class GenerateServiceImpl implements GenerateService {


    @Override
    public void generate(GenerateRequest request, ZipOutputStream outputStream) {


    }
}
