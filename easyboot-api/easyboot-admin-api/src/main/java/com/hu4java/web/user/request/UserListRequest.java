package com.hu4java.web.user.request;

import com.hu4java.base.request.PageRequest;
import com.hu4java.system.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * 用户列表请求参数
 * @author chenzhenhu
 */
@Getter
@Setter
public class UserListRequest extends PageRequest<User> {
    private static final long serialVersionUID = -5551100647237166109L;

    /** 姓名*/
    private String name;
    /** 手机号*/
    private String mobile;
    /** 邮箱*/
    private String email;
    /** 性别：0-未知 1-男 2-女*/
    private Integer gender;
    /** 员工编号*/
    private String code;
    /** 角色id*/
    private Long roleId;
    /** 部门id*/
    private Long deptId;
    /** 人事状态*/
    private Integer state;
    /** 生日*/
    private LocalDate birthday;

}
