package com.hu4java.web.user.response;

import com.hu4java.base.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author chenzhenhu
 */
@Getter
@Setter
public class RoleUpdateResponse extends BaseResponse {
    private static final long serialVersionUID = 4748047658299421862L;

    private Long id;
    /** 角色名称*/
    private String name;
    /** 角色代码*/
    private String code;
    /** 状态： 0-正常 1-禁用*/
    private Integer status;
    /** 备注*/
    private String remark;
    /** 关联菜单ID*/
    private List<Long> menuIds;
}
