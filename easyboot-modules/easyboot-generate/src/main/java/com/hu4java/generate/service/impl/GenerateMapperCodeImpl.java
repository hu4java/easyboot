package com.hu4java.generate.service.impl;

import com.hu4java.generate.helper.CodeHelper;
import com.hu4java.generate.request.GenerateRequest;
import com.hu4java.generate.service.GenerateService;

/**
 * @author chenzhenhu
 */
public class GenerateMapperImpl implements GenerateService {


    @Override
    public String generate(GenerateRequest request) {
        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package ").append(request.getJavaPackage()).append(".mapper;\n\n");

        // import
        sb.append("import com.hu4java.base.mapper.BaseMapper;\n");
        sb.append("import ").append(request.getJavaPackage()).append(".entity.").append(request.getEntityName()).append(";\n");
        sb.append("import org.springframework.stereotype.Repository;\n\n");

        // 注释
        sb.append(CodeHelper.buildClassComment(request.getComment(), request.getAuthor()));
        sb.append("@Repository\n");
        sb.append("public interface ").append(request.getEntityName()).append("Mapper extends BaseMapper<")
                .append(request.getEntityName()).append("> {\n\n}");
        return sb.toString();
    }
}
