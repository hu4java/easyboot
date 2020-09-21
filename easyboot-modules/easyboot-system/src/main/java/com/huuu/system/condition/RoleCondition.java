package com.huuu.system.condition;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huuu.base.condition.Condition;
import com.huuu.system.entity.Role;
import org.apache.commons.lang3.StringUtils;

/**
 * @author chenzhenhu
 */
public class RoleCondition extends Condition<Role> {

    private String name;

    private Integer status;

    @Override
    public LambdaQueryWrapper<Role> queryWrapper() {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.eq(Role::getName, name);
        }
        if (null != status) {
            queryWrapper.eq(Role::getStatus, status);
        }
        return queryWrapper;
    }
}
