package com.hu4java.web.user.request;

import com.hu4java.base.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色新增/更新数据
 * @author chenzhenhu
 */
@Getter
@Setter
public class RoleFormRequest extends BaseRequest {

    private static final long serialVersionUID = 3048189774432038555L;

    /** 角色名称*/
    @NotBlank(message = "角色名称不能为空")
    private String name;
    /** 角色代码*/
    @NotBlank(message = "角色代码不能为空")
    private String code;
    /** 状态： 0-正常 1-禁用*/
    @NotNull(message = "状态不能为空")
    private Integer status;
    /** 备注*/
    @Length(max = 50, message = "备注最多50个字符")
    private String remark;

    private List<Long> menuIds;
}
