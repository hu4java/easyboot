package com.hu4java.generate.helper;

import com.hu4java.common.constant.DateConstants;
import com.hu4java.generate.entity.Column;
import com.hu4java.generate.entity.Table;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhenhu
 */
public class CodeHelper {

    public static String buildEntity(String prefixPackage, Table table) {
        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package " + prefixPackage + ".entity;\n\n");

        // import
        sb.append("import com.baomidou.mybatisplus.annotation.TableName;\n");
        sb.append("import com.hu4java.base.entity.BaseEntity;\n");
        sb.append("import lombok.Getter;\n");
        sb.append("import lombok.Setter;\n\n");
        sb.append("import java.time.*;\n\n");

        // 类注释部分
        sb.append(buildClassComment(table.getComment()));

        // 类主体
        sb.append("@Getter\n");
        sb.append("@Setter\n");
        sb.append("@TableName(\"").append(table.getName()).append("\")\n");
        sb.append("public class ").append(table.entityName()).append(" extends BaseEntity {\n");
        sb.append("\tprivate static final long serialVersionUID = -1L;\n\n");

        // 字段
        for (Column column : table.getColumnList()) {
            String javaType = javaType(column.getType());
            sb.append("\t/** ").append(column.getComment()).append(" */\n");
            sb.append("\tprivate ").append(javaType).append(" ").append(column.getFieldName()).append(";\n\n");
        }

        sb.append("}");
        return sb.toString();
    }

    public static String buildMapper(String prefixPackage, Table table) {
        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package ").append(prefixPackage).append(".mapper;\n\n");

        // import
        sb.append("import com.hu4java.base.mapper.BaseMapper;\n");
        sb.append("import ").append(prefixPackage).append(".entity.").append(table.entityName()).append(";\n");
        sb.append("import org.springframework.stereotype.Repository;\n\n");

        // 注释
        sb.append(buildClassComment(table.getComment()));
        sb.append("@Repository\n");
        sb.append("public interface ").append(table.mapperName()).append(" extends BaseMapper<")
                .append(table.entityName()).append("> {\n\n}");
        return sb.toString();
    }

    public static String buildService(String prefixPackage, Table table) {
        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package ").append(prefixPackage).append(".service;\n\n");

        // import
        sb.append("import com.hu4java.base.service.BaseService;\n");
        sb.append("import ").append(prefixPackage).append(".entity.").append(table.entityName()).append(";\n");

        // 注释
        sb.append(buildClassComment(table.getComment()));
        sb.append("public interface ").append(table.serviceName()).append(" extends BaseService<")
                .append(table.entityName()).append("> {\n\n}");

        return sb.toString();
    }

    public static String buildServiceImpl(String prefixPackage, Table table) {
        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package ").append(prefixPackage).append(".service.impl;\n\n");

        // import
        sb.append("import com.hu4java.base.service.impl.AbstractServiceImpl;\n");
        sb.append("import ").append(prefixPackage).append(".service.").append(table.serviceName()).append(";\n");
        sb.append("import ").append(prefixPackage).append(".entity.").append(table.entityName()).append(";\n");
        sb.append("import ").append(prefixPackage).append(".mapper.").append(table.mapperName()).append(";\n");
        sb.append("import org.springframework.stereotype.Service;\n\n");

        // 注释
        sb.append(buildClassComment(table.getComment()));
        sb.append("@Service\n");
        sb.append("public class ").append(table.serviceImplName()).append(" extends AbstractServiceImpl<")
                .append(table.entityName()).append(", ")
                .append(table.mapperName()).append("> implements ")
                .append(table.serviceName()).append(" {\n\n}");
        return sb.toString();
    }

    public static String buildController(String prefixPackage, Table table) {
        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package ").append(prefixPackage).append(".controller;\n\n");

        // import
        sb.append("import ").append(prefixPackage).append(".service.").append(table.serviceName()).append(";\n");
        sb.append("import com.hu4java.common.result.Result;\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        sb.append("import org.springframework.web.bind.annotation.GetMapping;\n");
        sb.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
        sb.append("import org.springframework.web.bind.annotation.RestController;\n");

        // 注释
        sb.append(buildClassComment(table.getComment()));
        sb.append("@RestController\n");
        sb.append("@RequestMapping(\"/").append(table.getName()).append("\")\n");
        sb.append("public class ").append(table.controllerName()).append(" {\n\n}");
        return sb.toString();
    }

    /**
     * 类注释
     * @param comment   注释
     * @return
     */
    public static String buildClassComment(String comment) {
        String createTime = DateTimeFormatter.ofPattern(DateConstants.DEFAULT_DATE_TIME_FORMAT).format(LocalDateTime.now());
        StringBuilder sb = new StringBuilder();
        sb.append("/**\n");
        sb.append(" * ").append(comment).append("\n");
        sb.append(" * @author\tEasyBoot\n");
        sb.append(" * @date\t").append(createTime).append("\n");
        sb.append(" */\n");
        return sb.toString();
    }

    public static String javaType(String dataType) {
        switch (dataType) {
            case "int":
            case "smallint":
            case "tinyint":
                return "Integer";
            case "bigint":
                return "Long";
            case "time":
                return "LocalTime";
            case "date":
                return "LocalDate";
            case "datetime":
                return "LocalDateTime";
            default:
                return "String";
        }
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

        String entity = CodeHelper.buildEntity("com.hu4java.test", table);
        System.out.println(entity);

        String mapper = CodeHelper.buildMapper("com.hu4java.test", table);
        System.out.println(mapper);

        String service = CodeHelper.buildService("com.hu4java.test", table);
        System.out.println(service);

        String serviceImpl = CodeHelper.buildServiceImpl("com.hu4java.test", table);
        System.out.println(serviceImpl);

        String controller = CodeHelper.buildController("com.hu4java.test", table);
        System.out.println(controller);

        IOUtils.write(entity.getBytes("UTF-8"), new FileOutputStream(new File("d:/entity.java")));

    }
}
