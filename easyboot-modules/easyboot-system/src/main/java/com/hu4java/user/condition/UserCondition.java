package com.hu4java.user.condition;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hu4java.base.condition.Condition;
import com.hu4java.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author hu4java
 */
@Getter
@Setter
public class UserCondition extends Condition<User> {

    private String name;

    private String mobile;

    private String email;

    private Integer gender;

    @Override
    public LambdaQueryWrapper<User> queryWrapper() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like(User::getName, name);
        }
        if (StringUtils.isNotBlank(mobile)) {
            queryWrapper.or().eq(User::getMobile, mobile);
        }
        if (StringUtils.isNotBlank(email)) {
            queryWrapper.or().like(User::getEmail, email);
        }
        if (null != gender) {
            queryWrapper.or().eq(User::getGender, gender);
        }
        return queryWrapper;
    }
}
