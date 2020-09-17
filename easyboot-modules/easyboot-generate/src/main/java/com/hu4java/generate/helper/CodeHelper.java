package com.hu4java.generate.helper;

import com.hu4java.common.core.constant.DateConstants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author chenzhenhu
 */
public class CodeHelper {

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

    public static String javaPath(String javaPackage) {
        String path = javaPackage.replaceAll("\\.", "/");
        String javaPath = "src/main/java/" + path;
        return javaPath;
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
