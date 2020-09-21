package com.huuu.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huuu.base.service.Service;
import com.huuu.system.condition.UserCondition;
import com.huuu.system.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * 保存用户
     * @param user      用户
     * @param roleIds   角色id列表
     * @param deptIds   部门id列表
     */
    @Transactional(rollbackFor = Exception.class)
    void save(User user, List<Long> roleIds, List<Long> deptIds);

    /**
     * 更新用户
     * @param user      用户
     * @param roleIds   角色id列表
     * @param deptIds   部门id列表
     */
    @Transactional(rollbackFor = Exception.class)
    void update(User user, List<Long> roleIds, List<Long> deptIds);


    /**
     * 分页查询
     * @param page      分页
     * @param condition 条件参数
     * @return
     */
    Page<User> listByPage(Page<User> page, UserCondition condition);

    /**
     * 根据手机号查询
     * @param mobile    手机号
     * @return
     */
    User getByMobile(String mobile);

    /**
     * 根据邮箱查询
     * @param email 邮箱
     * @return
     */
    User getByEmail(String email);
}
