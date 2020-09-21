package com.huuu.generate.request;

import com.huuu.base.entity.BaseEntity;
import com.huuu.base.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author huuu
 */
@Getter
@Setter
public class GenerateRequest extends BaseRequest {
    private static final long serialVersionUID = 6820367315667062364L;

    /** 表名称*/
    @NotBlank(message = "表名称不能为空")
    private String tableName;
    /** 模块*/
    @NotBlank(message = "模块不能为空")
    private String module;
    /** 注释*/
    @NotBlank(message = "注释不能为空")
    private String comment;
    /** 包路径*/
    @NotBlank(message = "包路径不能为空")
    private String javaPackage;
    /** 实体类名称*/
    @NotBlank(message = "实体类名称不能为空")
    private String entityName;
    /** 作者*/
    @NotBlank(message = "作者不能为空")
    private String author;
    /** 字段列表*/
    @NotNull(message = "字段列表不能为空")
    private List<GenerateFieldRequest> fieldList;

    public List<GenerateFieldRequest> removeCommonField() {
        if (null == fieldList || fieldList.size() == 0) {
            return Collections.emptyList();
        }

        List<GenerateFieldRequest> list = new ArrayList<>();
        for (GenerateFieldRequest field : fieldList) {
            if (field.getFieldName().equals(BaseEntity.ID_FIELD)
                    || field.getFieldName().equals(BaseEntity.CREATE_BY_FIELD)
                    || field.getFieldName().equals(BaseEntity.CREATE_TIME_FIELD)
                    || field.getFieldName().equals(BaseEntity.UPDATE_BY_FIELD)
                    || field.getFieldName().equals(BaseEntity.UPDATE_TIME_FIELD)
                    || field.getFieldName().equals(BaseEntity.DELETE_FIELD)
                    || field.getFieldName().equals(BaseEntity.VERSION_FIELD)) {
                continue;
            }
            list.add(field);
        }
        return list;
    }
}
