package com.hu4java.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu4java.base.service.impl.AbstractServiceImpl;
import com.hu4java.system.condition.UserCondition;
import com.hu4java.system.entity.*;
import com.hu4java.system.mapper.*;
import com.hu4java.system.service.UserDeptService;
import com.hu4java.system.service.UserRoleService;
import com.hu4java.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserDeptService userDeptService;


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

    @Override
    public void save(User user, List<Long> roleIds, List<Long> deptIds) {
        mapper.insert(user);
        saveUserRole(user, roleIds);
        saveUserDept(user, deptIds);
    }

    @Override
    public void update(User user, List<Long> roleIds, List<Long> deptIds) {
        mapper.updateById(user);
        if (roleIds.size() > 0) {
            userRoleService.removeByUserId(user.getId());
        }
        if (deptIds.size() > 0) {
            userDeptService.removeByUserId(user.getId());
        }
        saveUserRole(user, roleIds);
        saveUserDept(user, deptIds);
    }

    @Override
    public Page<User> listByPage(Page<User> page, UserCondition condition) {
        return mapper.selectByPage(page, condition);
    }

    /**
     * 保存用户角色关系
     * @param user      用户
     * @param roleIds   角色
     */
    private void saveUserRole(User user, List<Long> roleIds) {
        if (null == roleIds || roleIds.size() == 0) {
            return;
        }
        List<UserRole> list = new ArrayList<>(roleIds.size());
        for (Long roleId : roleIds) {
            UserRole item = new UserRole();
            item.setRoleId(roleId);
            item.setUserId(user.getId());
            list.add(item);
        }
        userRoleService.saveBatch(list);
    }

    /**
     * 保存用户部门关系
     * @param user      用户
     * @param deptIds   部门
     */
    private void saveUserDept(User user, List<Long> deptIds) {
        if (null == deptIds || deptIds.size() == 0) {
            return;
        }
        List<UserDept> list = new ArrayList<>(deptIds.size());
        for (Long deptId : deptIds) {
            UserDept item = new UserDept();
            item.setDeptId(deptId);
            item.setUserId(user.getId());
            list.add(item);
        }
        userDeptService.saveBatch(list);
    }
}
