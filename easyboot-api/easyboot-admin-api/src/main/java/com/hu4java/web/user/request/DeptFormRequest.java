package com.hu4java.web.user.request;

import com.hu4java.base.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 部门表单
 * @author chenzhenhu
 */
@Getter
@Setter
public class DeptFormRequest extends BaseRequest {
    private static final long serialVersionUID = 1790949538205404769L;

    /** 部门名称*/
    @NotBlank(message = "部门名称不能为空")
    private String name;
    /** 部门编码*/
    private String code;
    /** 上级部门id*/
    @NotNull(message = "上级部门id不能为空")
    private Long pid;
    /** 联系电话*/
    private String phone;
    /** 邮箱*/
    @Email(message = "邮箱格式正确")
    private String email;
    /** 排序*/
    @NotNull(message = "排序不能为空")
    private Integer orderNum;
    /** 状态：0-正常 1-禁用*/
    @NotNull(message = "状态不能为空")
    private Integer status;
    /** 备注*/
    private String remark;
}
