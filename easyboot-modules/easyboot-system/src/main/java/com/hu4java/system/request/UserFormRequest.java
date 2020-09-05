package com.hu4java.system.request;

import com.hu4java.base.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * 用户表单参数
 * @author hu4java
 */
@Getter
@Setter
public class UserFormRequest extends BaseRequest {
    private static final long serialVersionUID = -2202732202685452958L;

    /** 姓名*/
    @NotBlank(message = "姓名不能为空")
    private String name;
    /** 员工编号*/
    private String code;
    /** 头像*/
    private String avatar;
    /** 性别：0-未知 1-男 2-女*/
    private Integer gender;
    /** 手机号*/
    @NotBlank(message = "手机号不能为空")
    private String mobile;
    /** 邮箱*/
    @NotBlank(message = "邮箱不能为空")
    private String email;
    /** 生日*/
    private LocalDate birthday;
    /** 人事状态*/
    @NotNull(message = "人事状态不能为空")
    private Integer state;
    /** 合同到期*/
    private LocalDate contractExpireDate;
    /** 角色id*/
    @NotNull(message = "角色不能为空")
    private List<Long> roleIds;
    /** 部门id*/
    @NotNull(message = "部门不能为空")
    private List<Long> deptIds;
}
