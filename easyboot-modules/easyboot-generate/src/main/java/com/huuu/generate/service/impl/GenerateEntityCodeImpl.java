package com.huuu.generate.service.impl;

import com.huuu.generate.helper.CodeHelper;
import com.huuu.generate.request.GenerateFieldRequest;
import com.huuu.generate.request.GenerateRequest;
import com.huuu.generate.service.GenerateCodeService;
import com.huuu.util.StrUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author chenzhenhu
 */
public class GenerateEntityCodeImpl implements GenerateCodeService {


    @Override
    public String generate(GenerateRequest request) {

        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package ").append(request.getJavaPackage()).append(".entity;\n\n");

        // import
        sb.append("import com.baomidou.mybatisplus.annotation.TableName;\n");
        sb.append("import com.baomidou.mybatisplus.annotation.TableField;\n");
        sb.append("import com.huuu.base.entity.BaseEntity;\n");
        sb.append("import lombok.Getter;\n");
        sb.append("import lombok.Setter;\n\n");
        sb.append("import java.time.*;\n\n");

        // 类注释部分
        sb.append(CodeHelper.buildClassComment(request.getComment(), request.getAuthor()));

        // 类主体
        sb.append("@Getter\n");
        sb.append("@Setter\n");
        sb.append("@TableName(\"").append(request.getTableName()).append("\")\n");
        sb.append("public class ").append(request.getEntityName()).append(" extends BaseEntity {\n");
        sb.append("\tprivate static final long serialVersionUID = -1L;\n\n");

        // 字段
        List<GenerateFieldRequest> fieldList = request.removeCommonField();
        for (GenerateFieldRequest field : fieldList) {
            sb.append("\t/** ").append(field.getColumnComment()).append("*/\n");
            // 字段名和列名是否相同
            String originalField = StrUtils.underlineToCamel(field.getColumnName(), false);
            if (!Objects.equals(originalField, field.getFieldName())) {
                sb.append("\t@TableField(value = \"").append(field.getColumnName()).append("\")\n");
            }
            sb.append("\tprivate ").append(field.getJavaType()).append(" ").append(field.getFieldName()).append(";\n\n");
        }

        sb.append("}");
        return sb.toString();
    }

    @Override
    public String path(GenerateRequest request) {

        return CodeHelper.javaPath(request.getJavaPackage()) + "/entity/" + request.getEntityName() + ".java";
    }
}
