package com.hu4java.system.request;

import com.hu4java.base.request.PageRequest;
import com.hu4java.system.entity.User;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chenzhenhu
 */
@Getter
@Setter
public class UserListRequest extends PageRequest<User> {
    private static final long serialVersionUID = -5551100647237166109L;

    private String name;

    private String mobile;

    private Long deptId;

}
