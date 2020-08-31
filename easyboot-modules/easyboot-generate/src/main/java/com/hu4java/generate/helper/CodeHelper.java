package com.hu4java.generate.helper;

import com.hu4java.common.constant.DateConstants;
import com.hu4java.generate.request.GenerateFieldRequest;
import com.hu4java.generate.request.GenerateRequest;
import com.hu4java.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * @author chenzhenhu
 */
public class CodeHelper {

    public static String buildEntity(String prefixPackage, GenerateRequest request) {
        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package ").append(prefixPackage).append(".entity;\n\n");

        // import
        sb.append("import com.baomidou.mybatisplus.annotation.TableName;\n");
        sb.append("import com.baomidou.mybatisplus.annotation.TableField;\n");
        sb.append("import com.hu4java.base.entity.BaseEntity;\n");
        sb.append("import lombok.Getter;\n");
        sb.append("import lombok.Setter;\n\n");
        sb.append("import java.time.*;\n\n");

        // 类注释部分
        sb.append(buildClassComment(request.getComment(), request.getAuthor()));

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
            String originalField = StringUtils.underlineToCamel(field.getColumnName(), false);
            if (!Objects.equals(originalField, field.getFieldName())) {
                sb.append("\t@TableField(value = \"").append(field.getColumnName()).append("\")\n");
            }
            sb.append("\tprivate ").append(field.getJavaType()).append(" ").append(field.getFieldName()).append(";\n\n");
        }

        sb.append("}");
        return sb.toString();
    }

    public static String buildMapper(String prefixPackage, GenerateRequest request) {
        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package ").append(prefixPackage).append(".mapper;\n\n");

        // import
        sb.append("import com.hu4java.base.mapper.BaseMapper;\n");
        sb.append("import ").append(prefixPackage).append(".entity.").append(request.getEntityName()).append(";\n");
        sb.append("import org.springframework.stereotype.Repository;\n\n");

        // 注释
        sb.append(buildClassComment(request.getComment(), request.getAuthor()));
        sb.append("@Repository\n");
        sb.append("public interface ").append(request.getEntityName()).append("Mapper extends BaseMapper<")
                .append(request.getEntityName()).append("> {\n\n}");
        return sb.toString();
    }

    public static String buildService(String prefixPackage, GenerateRequest request) {
        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package ").append(prefixPackage).append(".service;\n\n");

        // import
        sb.append("import com.hu4java.base.service.Service;\n");
        sb.append("import ").append(prefixPackage).append(".entity.").append(request.getEntityName()).append(";\n");

        // 注释
        sb.append(buildClassComment(request.getComment(), request.getAuthor()));
        sb.append("public interface ").append(request.getEntityName()).append("Service extends Service<")
                .append(request.getEntityName()).append("> {\n\n}");

        return sb.toString();
    }

    public static String buildServiceImpl(String prefixPackage, GenerateRequest request) {
        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package ").append(prefixPackage).append(".service.impl;\n\n");

        // import
        sb.append("import com.hu4java.base.service.impl.AbstractServiceImpl;\n");
        sb.append("import ").append(prefixPackage).append(".service.").append(request.getEntityName()).append("Service;\n");
        sb.append("import ").append(prefixPackage).append(".entity.").append(request.getEntityName()).append(";\n");
        sb.append("import ").append(prefixPackage).append(".mapper.").append(request.getEntityName()).append("Mapper;\n");
        sb.append("import org.springframework.stereotype.Service;\n\n");

        // 注释
        sb.append(buildClassComment(request.getComment(), request.getAuthor()));
        sb.append("@Service\n");
        sb.append("public class ").append(request.getEntityName()).append("ServiceImpl extends AbstractServiceImpl<")
                .append(request.getEntityName()).append(", ")
                .append(request.getEntityName()).append("Mapper> implements ")
                .append(request.getEntityName()).append("Service {\n\n}");
        return sb.toString();
    }

    public static String buildCondition(String prefixPackage, GenerateRequest request) {
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
        sb.append(buildClassComment(request.getComment(), request.getAuthor()));

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

    public static String buildController(String prefixPackage, GenerateRequest request) {
        String serviceInstanceName = instanceName(request.getEntityName()) + "Service";
        String conditionName = request.getEntityName() + "Condition";
        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package ").append(prefixPackage).append(".controller;\n\n");

        // import
        sb.append("import import com.baomidou.mybatisplus.extension.plugins.pagination.Page;\n");
        sb.append("import ").append(prefixPackage).append(".service.").append(request.getEntityName()).append("Service;\n");
        sb.append("import ").append(prefixPackage).append(".entity.").append(request.getEntityName()).append(";\n");
        sb.append("import ").append(prefixPackage).append(".condition.").append(conditionName).append(";\n");
        sb.append("import com.hu4java.common.result.Result;\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        sb.append("import org.springframework.web.bind.annotation.GetMapping;\n");
        sb.append("import org.springframework.web.bind.annotation.PostMapping;\n");
        sb.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
        sb.append("import org.springframework.web.bind.annotation.RestController;\n");

        // 注释
        sb.append(buildClassComment(request.getComment(), request.getAuthor()));
        sb.append("@RestController\n");
        sb.append("@RequestMapping(\"/").append(request.getModule()).append("/")
                .append(request.getEntityName()).append("\")\n");
        sb.append("public class ").append(request.getEntityName()).append("Controller {\n\n");
        sb.append("\t@Autowired\n");
        sb.append("\tprivate ").append(request.getEntityName()).append("Service ").append(serviceInstanceName).append(";\n\n");
        sb.append("\t@GetMapping(\"/list\")\n");
        sb.append("\tpublic Result<Page<").append(request.getEntityName()).append(">> list(Page<")
                .append(request.getEntityName()).append("> page, ").append(conditionName).append(" condition) {\n");
        sb.append("\t\tpage = ").append(serviceInstanceName).append(".listByPage(page, condition);\n");
        sb.append("\t\treturn Result.success(page);\n");
        sb.append("\t}\n");
        sb.append("\n}");
        return sb.toString();
    }

    /**
     * 类注释
     * @param comment   注释
     * @return
     */
    public static String buildClassComment(String comment, String author) {
        String createTime = DateTimeFormatter.ofPattern(DateConstants.DEFAULT_DATE_TIME_FORMAT).format(LocalDateTime.now());
        StringBuilder sb = new StringBuilder();
        sb.append("/**\n");
        sb.append(" * ").append(comment).append("\n");
        sb.append(" * @author\t").append(author).append("\n");
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

    public static String instanceName(String name) {
        if (null == name || "".equals(name.trim())) {
            return null;
        }
        char[] chars = name.toCharArray();

        StringBuilder sb = new StringBuilder(chars.length);

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (i == 0) {
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
