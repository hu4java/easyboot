package com.huuu.generate.request;

import com.huuu.base.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author huuu
 */
@Getter
@Setter
public class TableInfoRequest extends BaseRequest {
    private static final long serialVersionUID = -3476920031295578984L;

    /** 表名*/
    @NotBlank(message = "表名不能为空")
    private String tableName;
}
