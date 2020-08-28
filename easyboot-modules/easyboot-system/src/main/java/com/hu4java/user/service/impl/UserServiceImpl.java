package com.hu4java.user.service.impl;

import com.hu4java.base.service.impl.AbstractServiceImpl;
import com.hu4java.user.entity.Dept;
import com.hu4java.user.entity.Menu;
import com.hu4java.user.entity.Role;
import com.hu4java.user.entity.User;
import com.hu4java.user.mapper.DeptMapper;
import com.hu4java.user.mapper.MenuMapper;
import com.hu4java.user.mapper.RoleMapper;
import com.hu4java.user.mapper.UserMapper;
import com.hu4java.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenzhenhu
 */
@Service
public class UserServiceImpl extends AbstractServiceImpl<User, UserMapper> implements UserService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public User getByUsername(String username) {
        User user = mapper.selectByUsername(username);
        if (null == user) {
            return null;
        }

        List<Role> roleList = roleMapper.selectByUserId(user.getId());
        List<Menu> menuList = menuMapper.selectByUserId(user.getId());
        List<Dept> deptList = deptMapper.selectByUserId(user.getId());

        user.setRoleList(roleList);
        user.setMenuList(menuList);
        user.setDeptList(deptList);
        return user;
    }
}
