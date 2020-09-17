package com.hu4java.generate.service.impl;

import com.hu4java.generate.helper.CodeHelper;
import com.hu4java.generate.request.GenerateFieldRequest;
import com.hu4java.generate.request.GenerateRequest;
import com.hu4java.generate.service.GenerateCodeService;

import java.util.List;

/**
 * @author chenzhenhu
 */
public class GenerateConditionCodeImpl implements GenerateCodeService {


    @Override
    public String generate(GenerateRequest request) {
        String prefixPackage = request.getJavaPackage();
        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package ").append(prefixPackage).append(".condition;\n\n");

        // import
        sb.append("import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;\n");
        sb.append("import com.hu4java.base.condition.Condition;\n");
        sb.append("import ").append(prefixPackage).append(".entity.").append(request.getEntityName()).append(";\n");
        sb.append("import lombok.Getter;\n");
        sb.append("import lombok.Setter;\n\n");
        sb.append("import java.time.*;\n\n");

        // 类注释部分
        sb.append(CodeHelper.buildClassComment(request.getComment(), request.getAuthor()));

        // 类主体
        sb.append("@Getter\n");
        sb.append("@Setter\n");
        sb.append("public class ").append(request.getEntityName()).append("Condition extends Condition {\n");
        sb.append("\tprivate static final long serialVersionUID = -1L;\n\n");

        // 字段
        List<GenerateFieldRequest> fieldList = request.removeCommonField();
        for (GenerateFieldRequest field : fieldList) {
            sb.append("\t/** ").append(field.getColumnComment()).append("*/\n");
            sb.append("\tprivate ").append(field.getJavaType()).append(" ").append(field.getFieldName()).append(";\n\n");
        }

        sb.append("\t@Override\n");
        sb.append("\tpublic LambdaQueryWrapper<").append(request.getEntityName()).append("> queryWrapper() {\n");
        sb.append("\t\treturn null;\n");
        sb.append("\t}\n\n");

        sb.append("}");
        return sb.toString();
    }

    @Override
    public String path(GenerateRequest request) {
        return CodeHelper.javaPath(request.getJavaPackage()) + "/condition/" + request.getEntityName() + "Condition.java";
    }
}
