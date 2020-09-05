package com.hu4java.system.request;

import com.hu4java.base.request.PageRequest;
import com.hu4java.system.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author chenzhenhu
 */
@Getter
@Setter
public class UserListRequest extends PageRequest<User> {
    private static final long serialVersionUID = -5551100647237166109L;

    private String name;

    private String mobile;

    private String email;

    private Integer gender;

    private String code;

    private Long roleId;

    private Long deptId;

    private Integer state;

    private LocalDate startBirthday;

    private LocalDate endBirthday;

}
