package com.hu4java.generate.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 表
 * @author chenzhenhu
 */
@Getter
@Setter
public class Table {

    /** 表名*/
    private String tableName;
    /** 注释*/
    private String tableComment;
    /** 数据库*/
    private String tableSchema;
    /** 创建时间*/
    private LocalDateTime createTime;
    /** 字符集*/
    private String tableCollation;

    private List<Column> columnList;

//    public String entityName() {
//        if (null == name || "".equals(name.trim())) {
//            return null;
//        }
//        if (removePrefix) {
//            String[] names = name.split("_");
//            String string = name.replace(names[0] + "_", "");
//            return StringUtils.underlineToCamel(string, true);
//        }
//        return StringUtils.underlineToCamel(name, true);
//    }
//
//    public String mapperName() {
//        String entityName = entityName();
//        if (null == entityName) {
//            return null;
//        }
//        return entityName + "Mapper";
//    }
//
//    public String serviceName() {
//        String entityName = entityName();
//        if (null == entityName) {
//            return null;
//        }
//        return entityName + "Service";
//    }
//
//    public String serviceImplName() {
//        String entityName = entityName();
//        if (null == entityName) {
//            return null;
//        }
//        return entityName + "ServiceImpl";
//    }
//
//    public String controllerName() {
//        String entityName = entityName();
//        if (null == entityName) {
//            return null;
//        }
//        return entityName + "Controller";
//    }
//
//    public String typeName() {
//        if (null == name || "".equals(name.trim())) {
//            return null;
//        }
//
//        if (removePrefix) {
//            String[] names = name.split("_");
//            String string = name.replace(names[0] + "_", "");
//            return StringUtils.underlineToCamel(string, false);
//        }
//        return StringUtils.underlineToCamel(name, false);
//    }
//
//    public String resultMapName() {
//
//        String typeName = typeName();
//        if (null == typeName) {
//            return null;
//        }
//        return typeName + "Map";
//    }
}
