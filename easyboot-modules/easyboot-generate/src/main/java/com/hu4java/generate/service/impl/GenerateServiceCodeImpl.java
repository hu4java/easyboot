package com.hu4java.generate.service.impl;

import com.hu4java.generate.helper.CodeHelper;
import com.hu4java.generate.request.GenerateRequest;
import com.hu4java.generate.service.GenerateCodeService;

/**
 * @author chenzhenhu
 */
public class GenerateServiceCodeImpl implements GenerateCodeService {


    @Override
    public String generate(GenerateRequest request) {
        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package ").append(request.getJavaPackage()).append(".service;\n\n");

        // import
        sb.append("import com.hu4java.base.service.Service;\n");
        sb.append("import ").append(request.getJavaPackage()).append(".entity.").append(request.getEntityName()).append(";\n");

        // 注释
        sb.append(CodeHelper.buildClassComment(request.getComment(), request.getAuthor()));
        sb.append("public interface ").append(request.getEntityName()).append("Service extends Service<")
                .append(request.getEntityName()).append("> {\n\n}");

        return sb.toString();
    }

    @Override
    public String path(GenerateRequest request) {

        return CodeHelper.javaPath(request.getJavaPackage()) + "/service/" + request.getEntityName() + "Service.java";
    }
}
