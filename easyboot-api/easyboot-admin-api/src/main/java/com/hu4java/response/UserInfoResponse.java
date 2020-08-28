package com.hu4java.response;

import com.hu4java.base.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * 用户数据
 * @author hu4java
 */
@Getter
@Setter
public class UserInfoResponse extends BaseResponse {
    private static final long serialVersionUID = -1234754822650659311L;

    /** 用户id*/
    private Long id;
    /** 姓名*/
    private String name;
    /** 用户名*/
    private String username;
    /** 头像*/
    private String avatar;
    /** 手机号*/
    private String mobile;
    /** 邮箱*/
    private String email;
    /** 性别*/
    private Integer gender;
    /** 生日*/
    private LocalDate birthday;
    /** 角色列表*/
    private List<String> roles;
    /** 权限列表*/
    private List<String> permissions;
}
