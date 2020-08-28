package com.hu4java.generate.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 列
 * @author chenzhenhu
 */
@Getter
@Setter
public class Column {

    private String tableName;

    private String name;

    private String comment;

    private String type;
}
