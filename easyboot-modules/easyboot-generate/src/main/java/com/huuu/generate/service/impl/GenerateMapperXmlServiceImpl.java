package com.huuu.generate.service.impl;

import com.huuu.generate.helper.CodeHelper;
import com.huuu.generate.request.GenerateFieldRequest;
import com.huuu.generate.request.GenerateRequest;
import com.huuu.generate.service.GenerateXmlService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.List;

/**
 * @author chenzhenhu
 */
public class GenerateMapperXmlServiceImpl implements GenerateXmlService {


    @Override
    public Document generateXml(GenerateRequest request) {
        Document document = DocumentHelper.createDocument();
        document.addDocType("mapper", "-//mybatis.org//DTD Mapper 3.0//EN", "http://mybatis.org/dtd/mybatis-3-mapper.dtd");
        Element mapper = document.addElement("mapper");
        mapper.addAttribute("namespace", request.getJavaPackage() + ".mapper." + request.getEntityName() + "Mapper");
        Element resultMap = mapper.addElement("resultMap");
        String instanceName = CodeHelper.instanceName(request.getEntityName());
        resultMap.addAttribute("id", instanceName + "Map");
        resultMap.addAttribute("type", instanceName);
        resultMap.addAttribute("extends", "BaseEntity.entity");

        Element allColumnSql = mapper.addElement("sql");
        allColumnSql.addAttribute("id", "allColumnSql");

        StringBuilder sb = new StringBuilder("\n");
        sb.append("\t\tt.id,\n");
        sb.append("\t\tt.create_by,\n");
        sb.append("\t\tt.create_time,\n");
        sb.append("\t\tt.update_by,\n");
        sb.append("\t\tt.update_time,\n");
        sb.append("\t\tt.is_delete,\n");
        sb.append("\t\tt.version,\n");

        List<GenerateFieldRequest> fieldList = request.removeCommonField();

        for (GenerateFieldRequest field : fieldList) {
            resultMap.addComment(field.getColumnComment());
            Element result = resultMap.addElement("result");
            result.addAttribute("property", field.getFieldName());
            result.addAttribute("column", field.getColumnName());
            sb.append("\t\tt.").append(field.getColumnName()).append(",\n");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append("\n\n\t");
        allColumnSql.addText(sb.toString());
        return document;
    }

    @Override
    public String path(GenerateRequest request) {
        return "src/main/resources/mapper/" + request.getModule() + "/" + request.getEntityName() + "Mapper.xml";
    }
}
