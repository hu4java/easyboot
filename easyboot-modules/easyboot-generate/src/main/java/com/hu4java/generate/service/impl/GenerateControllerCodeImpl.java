package com.hu4java.generate.service.impl;

import com.hu4java.generate.helper.CodeHelper;
import com.hu4java.generate.request.GenerateRequest;
import com.hu4java.generate.service.GenerateCodeService;

/**
 * @author chenzhenhu
 */
public class GenerateControllerCodeImpl implements GenerateCodeService {



    @Override
    public String generate(GenerateRequest request) {
        String prefixPackage = request.getJavaPackage();

        String serviceInstanceName = CodeHelper.instanceName(request.getEntityName()) + "Service";
        String conditionName = request.getEntityName() + "Condition";
        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package ").append(prefixPackage).append(".controller;\n\n");

        // import
        sb.append("import com.baomidou.mybatisplus.extension.plugins.pagination.Page;\n");
        sb.append("import ").append(prefixPackage).append(".service.").append(request.getEntityName()).append("Service;\n");
        sb.append("import ").append(prefixPackage).append(".entity.").append(request.getEntityName()).append(";\n");
        sb.append("import ").append(prefixPackage).append(".condition.").append(conditionName).append(";\n");
        sb.append("import com.hu4java.base.result.Result;\n");
        sb.append("import com.hu4java.base.request.PageRequest;\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        sb.append("import org.springframework.web.bind.annotation.GetMapping;\n");
        sb.append("import org.springframework.web.bind.annotation.PostMapping;\n");
        sb.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
        sb.append("import org.springframework.web.bind.annotation.RestController;\n");

        // 注释
        sb.append(CodeHelper.buildClassComment(request.getComment(), request.getAuthor()));
        sb.append("@RestController\n");
        sb.append("@RequestMapping(\"/").append(request.getModule()).append("/")
                .append(request.getEntityName()).append("\")\n");
        sb.append("public class ").append(request.getEntityName()).append("Controller {\n\n");
        sb.append("\t@Autowired\n");
        sb.append("\tprivate ").append(request.getEntityName()).append("Service ").append(serviceInstanceName).append(";\n\n");
        sb.append("\t@GetMapping(\"/list\")\n");
        sb.append("\tpublic Result<Page<").append(request.getEntityName()).append(">> list(PageRequest<")
                .append(request.getEntityName()).append("> request) {\n");
        sb.append("\t\tPage<").append(request.getEntityName()).append("> page = request.toPage();");
        sb.append("\t\tpage = ").append(serviceInstanceName).append(".listByPage(page, request.queryWrapper());\n");
        sb.append("\t\treturn Result.success(page);\n");
        sb.append("\t}\n");
        sb.append("\n}");
        return sb.toString();
    }

    @Override
    public String path(GenerateRequest request) {
        return CodeHelper.javaPath(request.getJavaPackage()) + "/controller/" + request.getEntityName() + "Controller.java";
    }
}
