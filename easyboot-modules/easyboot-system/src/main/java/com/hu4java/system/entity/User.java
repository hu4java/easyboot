package com.hu4java.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hu4java.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * 用户
 * @author chenzhenhu
 */
@Getter
@Setter
@TableName("sys_user")
public class User extends BaseEntity {
    private static final long serialVersionUID = -3225907634106465483L;

    /** 用户名*/
    private String username;
    /** 密码*/
    private String password;
    /** 加密盐*/
    private String salt;
    /** 姓名*/
    private String name;
    /** 员工编号*/
    private String code;
    /** 头像*/
    private String avatar;
    /** 性别：0-未知 1-男 2-女*/
    private Integer gender;
    /** 手机号*/
    private String mobile;
    /** 邮箱*/
    private String email;
    /** 生日*/
    private LocalDate birthday;
    /** 状态：0-正常 1-禁用*/
    private Integer status;
    /** 人事状态*/
    private Integer state;
    /** 合同到期*/
    private LocalDate contractExpireDate;

    @TableField(exist = false)
    private List<Role> roleList;
    @TableField(exist = false)
    private List<Menu> menuList;
    @TableField(exist = false)
    private List<Dept> deptList;
}
