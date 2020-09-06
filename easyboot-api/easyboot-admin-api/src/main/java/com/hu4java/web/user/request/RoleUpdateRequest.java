package com.hu4java.web.user.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 角色更新请求数据
 * @author chenzhenhu
 */
@Getter
@Setter
public class RoleUpdateRequest extends RoleFormRequest {
    private static final long serialVersionUID = 4206369063077439166L;
    /** 角色ID*/
    @NotNull(message = "角色ID不能为空")
    private Long id;
}
