package com.huuu.generate.service.impl;

import com.huuu.generate.helper.CodeHelper;
import com.huuu.generate.request.GenerateRequest;
import com.huuu.generate.service.GenerateCodeService;

/**
 * @author chenzhenhu
 */
public class GenerateServiceImplCodeImpl implements GenerateCodeService {


    @Override
    public String generate(GenerateRequest request) {
        String prefixPackage = request.getJavaPackage();
        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package ").append(prefixPackage).append(".service.impl;\n\n");

        // import
        sb.append("import com.huuu.base.service.impl.AbstractServiceImpl;\n");
        sb.append("import ").append(prefixPackage).append(".service.").append(request.getEntityName()).append("Service;\n");
        sb.append("import ").append(prefixPackage).append(".entity.").append(request.getEntityName()).append(";\n");
        sb.append("import ").append(prefixPackage).append(".mapper.").append(request.getEntityName()).append("Mapper;\n");
        sb.append("import org.springframework.stereotype.Service;\n\n");

        // 注释
        sb.append(CodeHelper.buildClassComment(request.getComment(), request.getAuthor()));
        sb.append("@Service\n");
        sb.append("public class ").append(request.getEntityName()).append("ServiceImpl extends AbstractServiceImpl<")
                .append(request.getEntityName()).append(", ")
                .append(request.getEntityName()).append("Mapper> implements ")
                .append(request.getEntityName()).append("Service {\n\n}");
        return sb.toString();
    }

    @Override
    public String path(GenerateRequest request) {
        return CodeHelper.javaPath(request.getJavaPackage()) + "/service/impl/" + request.getEntityName() + "ServiceImpl.java";
    }
}
