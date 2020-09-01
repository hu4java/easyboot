package com.hu4java.system.service;

import com.hu4java.base.service.Service;
import com.hu4java.system.entity.User;

/**
 * @author chenzhenhu
 */
public interface UserService extends Service<User> {

    /**
     * 根据用户名查询
     * @param username  用户名
     * @return          用户对象
     */
    User getByUsername(String username);
}
