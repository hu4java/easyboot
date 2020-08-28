package com.hu4java.generate.helper;

import com.hu4java.common.constant.DateConstants;
import com.hu4java.generate.entity.Column;
import com.hu4java.generate.entity.Table;

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
        sb.append("@TableName(\"" + table.getName() + "\")\n");
        sb.append("public class " + table.entityName() + " extends BaseEntity {\n");
        sb.append("\tprivate static final long serialVersionUID = -1L;\n\n");

        // 字段
        for (Column column : table.getColumnList()) {
            String javaType = javaType(column.getType());
            sb.append("\t/** " + column.getComment() + " */\n");
            sb.append("\tprivate " + javaType + " " + column.getFieldName() + ";\n\n");
        }

        sb.append("}");
        return sb.toString();
    }

    public static String buildMapper(String prefixPackage, Table table) {
        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package " + prefixPackage + ".mapper;\n\n");

        // import
        sb.append("import com.hu4java.base.mapper.BaseMapper;\n");
        sb.append("import " + prefixPackage + ".entity."+ table.entityName() +";\n");
        sb.append("import org.springframework.stereotype.Repository;\n\n");

        // 注释
        sb.append(buildClassComment(table.getComment()));
        sb.append("@Repository\n");
        sb.append("public interface " + table.mapperName() + " extends BaseMapper<"+ table.entityName() +"> {\n\n}");
        return sb.toString();
    }

    public static String buildService(String prefixPackage, Table table) {
        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package " + prefixPackage + ".service;\n\n");

        // import
        sb.append("import com.hu4java.base.service.BaseService;\n");
        sb.append("import " + prefixPackage + ".entity."+ table.entityName() +";\n");

        // 注释
        sb.append(buildClassComment(table.getComment()));
        sb.append("public interface " + table.serviceName() + " extends BaseService<"+ table.entityName() +"> {\n\n}");
        return sb.toString();
    }

    public static String buildServiceImpl(String prefixPackage, Table table) {
        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package " + prefixPackage + ".service.impl;\n\n");

        // import
        sb.append("import com.hu4java.base.service.impl.AbstractServiceImpl;\n");
        sb.append("import " + prefixPackage + ".service."+ table.serviceName() +";\n");
        sb.append("import " + prefixPackage + ".entity."+ table.entityName() +";\n");
        sb.append("import " + prefixPackage + ".mapper."+ table.mapperName() +";\n");
        sb.append("import org.springframework.stereotype.Service;\n\n");

        // 注释
        sb.append(buildClassComment(table.getComment()));
        sb.append("@Service\n");
        sb.append("public class " + table.serviceImplName()
                + " extends AbstractServiceImpl<" + table.entityName() +", "
                + table.mapperName() +"> implements "
                + table.serviceName() + " {\n\n}");
        return sb.toString();
    }

    public static String buildController(String prefixPackage, Table table) {
        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package " + prefixPackage + ".controller;\n\n");

        // import
        sb.append("import " + prefixPackage + ".service." + table.serviceName() + ";\n");
        sb.append("import com.hu4java.common.result.Result;\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        sb.append("import org.springframework.web.bind.annotation.GetMapping;\n");
        sb.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
        sb.append("import org.springframework.web.bind.annotation.RestController;\n");

        // 注释
        sb.append(buildClassComment(table.getComment()));
        sb.append("@RestController\n");
        sb.append("@RequestMapping(\"/" + table.getName() +"\")\n");
        sb.append("public class " + table.controllerName() + "{\n\n}");
        return sb.toString();
    }

    public static String buildClassComment(String comment) {
        String createTime = DateTimeFormatter.ofPattern(DateConstants.DEFAULT_DATE_TIME_FORMAT).format(LocalDateTime.now());
        StringBuilder sb = new StringBuilder();
        sb.append("/**\n");
        sb.append(" * " + comment + "\n");
        sb.append(" * @author\tEasyBoot生成\n");
        sb.append(" * @date\t" + createTime + "\n");
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

    public static void main(String[] args) {
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

    }
}
