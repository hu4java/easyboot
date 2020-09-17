package com.hu4java.generate.service;

import com.hu4java.generate.request.GenerateRequest;
import org.dom4j.Document;

/**
 * @author chenzhenhu
 */
public interface GenerateXmlService {

    /**
     * 构建xml
     * @param request
     * @return
     */
    Document generateXml(GenerateRequest request);

    /**
     * xml路径
     * @param request
     * @return
     */
    String path(GenerateRequest request);
}
