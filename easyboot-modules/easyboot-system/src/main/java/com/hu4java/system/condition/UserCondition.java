package com.hu4java.system.condition;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hu4java.base.condition.Condition;
import com.hu4java.system.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

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

    private String code;

    private Long roleId;

    private Long deptId;

    private Integer state;

    private LocalDate birthday;

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
