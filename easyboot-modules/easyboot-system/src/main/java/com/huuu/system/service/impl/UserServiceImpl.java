package com.huuu.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huuu.base.service.impl.AbstractServiceImpl;
import com.huuu.common.core.constant.Constants;
import com.huuu.system.condition.UserCondition;
import com.huuu.system.entity.Dept;
import com.huuu.system.entity.User;
import com.huuu.system.entity.UserDept;
import com.huuu.system.entity.UserRole;
import com.huuu.system.mapper.DeptMapper;
import com.huuu.system.mapper.UserMapper;
import com.huuu.system.service.UserDeptService;
import com.huuu.system.service.UserRoleService;
import com.huuu.system.service.UserService;
import com.huuu.util.RandomUtils;
import com.huuu.util.ShiroUtils;
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
        List<Dept> deptList = deptMapper.selectByUserId(user.getId());
        user.setDeptList(deptList);
        return user;
    }

    @Override
    public void save(User user, List<Long> roleIds, List<Long> deptIds) {
        String salt = RandomUtils.randomString(Constants.SALT_LENGTH);
        String password = ShiroUtils.encode(user.getPassword(), salt);
        user.setPassword(password);
        user.setSalt(salt);
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

    @Override
    public User getByMobile(String mobile) {

        return mapper.selectByMobile(mobile);
    }

    @Override
    public User getByEmail(String email) {
        return mapper.selectByEmail(email);
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
