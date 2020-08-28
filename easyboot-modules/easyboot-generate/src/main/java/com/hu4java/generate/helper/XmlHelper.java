package com.hu4java.generate.helper;

import com.hu4java.generate.entity.Column;
import com.hu4java.generate.entity.Table;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * @author chenzhenhu
 */
public class XmlHelper {

    public static Document buildMapperXml(String prefixPackage, Table table) {
        Document document = DocumentHelper.createDocument();
        document.addDocType("mapper", "-//mybatis.org//DTD Mapper 3.0//EN", "http://mybatis.org/dtd/mybatis-3-mapper.dtd");
        Element mapper = document.addElement("mapper");
        mapper.addAttribute("namespace", prefixPackage + ".mapper." + table.mapperName());
        Element resultMap = mapper.addElement("resultMap");
        resultMap.addAttribute("id", table.resultMapName());
        resultMap.addAttribute("type", table.typeName());
        resultMap.addAttribute("extends", "BaseEntity.entity");

        StringBuilder sb = new StringBuilder();
        for (Column column : table.getColumnList()) {
            Element result = resultMap.addElement("result");
            result.addAttribute("property", column.getFieldName());
            result.addAttribute("column", column.getName());
            sb.append(column.getName()).append(",\n");
        }

        Element allColumnSql = mapper.addElement("sql");
        allColumnSql.addElement("id", "allColumnSql");
        allColumnSql.setText(sb.toString());

        return document;
    }

    public static void main(String[] args) throws Exception {
        Table table = new Table();
        table.setComment("表");
        table.setName("sys_table");
        table.setRemovePrefix(true);
        List<Column> columnList = new ArrayList<>();
        Column column = new Column();
        column.setComment("列");
        column.setName("id");
        column.setType("bigint");
        columnList.add(column);
        Column column2 = new Column();
        column2.setComment("创建人");
        column2.setName("create_by");
        column2.setType("varchar");
        columnList.add(column2);
        Column column3 = new Column();
        column3.setComment("删除");
        column3.setName("delete");
        column3.setType("tinyint");
        columnList.add(column3);
        table.setColumnList(columnList);

        Document document = XmlHelper.buildMapperXml("com.hu4java.test", table);
        System.out.println(document.asXML());

        OutputFormat format = new OutputFormat();
        format.setIndentSize(4);  // 行缩进
        format.setNewlines(true); // 一个结点为一行
        format.setTrimText(true); // 去重空格
        format.setPadText(true);
        format.setNewLineAfterDeclaration(true); // 放置xml文件中第二行为空白行
        XMLWriter xmlWriter= new XMLWriter(new FileOutputStream(new File("/Users/john/mapper.xml")), format);
        xmlWriter.write(document);
    }
}
