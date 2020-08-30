package com.hu4java.generate.helper;

import com.hu4java.generate.request.GenerateFieldRequest;
import com.hu4java.generate.request.GenerateRequest;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.List;


/**
 * @author chenzhenhu
 */
public class XmlHelper {

    public static Document buildMapper(String prefixPackage, GenerateRequest request) {
        Document document = DocumentHelper.createDocument();
        document.addDocType("mapper", "-//mybatis.org//DTD Mapper 3.0//EN", "http://mybatis.org/dtd/mybatis-3-mapper.dtd");
        Element mapper = document.addElement("mapper");
        mapper.addAttribute("namespace", prefixPackage + ".mapper." + request.getEntityName() + "Mapper");
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

//    public static void main(String[] args) throws Exception {
//        Table table = new Table();
//        table.setComment("表");
//        table.setName("sys_table");
//        table.setRemovePrefix(true);
//        List<Column> columnList = new ArrayList<>();
//        Column column = new Column();
//        column.setComment("列");
//        column.setName("id");
//        column.setType("bigint");
//        columnList.add(column);
//        Column column2 = new Column();
//        column2.setComment("创建人");
//        column2.setName("create_by");
//        column2.setType("varchar");
//        columnList.add(column2);
//        Column column3 = new Column();
//        column3.setComment("删除");
//        column3.setName("delete");
//        column3.setType("tinyint");
//        columnList.add(column3);
//        table.setColumnList(columnList);
//
//        Document document = XmlHelper.buildMapper("com.hu4java.test", table);
//        System.out.println(document.asXML());
//
//        OutputFormat format = new OutputFormat();
//        format.setIndentSize(4);  // 行缩进
//        format.setNewlines(true); // 一个结点为一行
//        format.setTrimText(false); // 去重空格
//        format.setPadText(true);
//        format.setNewLineAfterDeclaration(true); // 放置xml文件中第二行为空白行
//        XMLWriter xmlWriter= new XMLWriter(new FileOutputStream(new File("d:/mapper.xml")), format);
//        xmlWriter.write(document);
//    }
}
