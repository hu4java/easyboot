package com.huuu.generate.service.impl;

import com.huuu.generate.helper.CodeHelper;
import com.huuu.generate.request.GenerateRequest;
import com.huuu.generate.service.GenerateCodeService;

/**
 * @author chenzhenhu
 */
public class GenerateMapperCodeImpl implements GenerateCodeService {


    @Override
    public String generate(GenerateRequest request) {
        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package ").append(request.getJavaPackage()).append(".mapper;\n\n");

        // import
        sb.append("import com.huuu.base.mapper.BaseMapper;\n");
        sb.append("import ").append(request.getJavaPackage()).append(".entity.").append(request.getEntityName()).append(";\n");
        sb.append("import org.springframework.stereotype.Repository;\n\n");

        // 注释
        sb.append(CodeHelper.buildClassComment(request.getComment(), request.getAuthor()));
        sb.append("@Repository\n");
        sb.append("public interface ").append(request.getEntityName()).append("Mapper extends BaseMapper<")
                .append(request.getEntityName()).append("> {\n\n}");
        return sb.toString();
    }

    @Override
    public String path(GenerateRequest request) {
        return CodeHelper.javaPath(request.getJavaPackage()) + "/mapper/" + request.getEntityName() + "Mapper.java";
    }
}
