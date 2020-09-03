package com.hu4java.system.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author chenzhenhu
 */
@Getter
@Setter
public class DeptUpdateRequest extends DeptRequest {
    private static final long serialVersionUID = 7010730087097940556L;

    /** 部门ID*/
    @NotNull(message = "部门ID不能为空")
    private Long id;
}
