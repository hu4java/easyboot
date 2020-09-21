package com.huuu.base.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础类
 * @author chenzhenhu
 */
@Getter
@Setter
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = -5051379387422169714L;

    public static final String ID_FIELD = "id";
    public static final String CREATE_BY_FIELD = "createBy";
    public static final String CREATE_TIME_FIELD = "createTime";
    public static final String UPDATE_BY_FIELD = "updateBy";
    public static final String UPDATE_TIME_FIELD = "updateTime";
    public static final String DELETE_FIELD = "isDelete";
    public static final String VERSION_FIELD = "version";

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Long updateBy;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "is_delete")
    private Boolean isDelete;

    private Integer version;
}
