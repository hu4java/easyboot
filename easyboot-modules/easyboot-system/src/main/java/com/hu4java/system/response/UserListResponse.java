package com.hu4java.system.response;

import com.hu4java.base.response.BaseResponse;
import com.hu4java.system.enums.UserState;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author chenzhenhu
 */
@Getter
@Setter
public class UserListResponse extends BaseResponse {
    private static final long serialVersionUID = 2660004492439639073L;

    /** 用户ID*/
    private Long id;
    /** 创建时间*/
    private LocalDateTime createTime;
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
    /** 人事状态*/
    private Integer state;
    /** 合同到期*/
    private LocalDate contractExpireDate;

    private String stateFormat;

    public String getMobile() {
        if (StringUtils.isNotBlank(mobile)) {
            mobile = mobile.replaceAll("(\\w{3})\\w*(\\w{4})", "$1****$2");
        }
        return mobile;
    }

    public String getStateFormat() {
        return UserState.find(state).getDesc();
    }
}
