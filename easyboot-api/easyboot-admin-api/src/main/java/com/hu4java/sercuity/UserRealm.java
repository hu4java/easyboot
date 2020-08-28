package com.hu4java.sercuity;

import com.hu4java.base.enums.Status;
import com.hu4java.common.result.ResultCode;
import com.hu4java.user.entity.Menu;
import com.hu4java.user.entity.Role;
import com.hu4java.user.entity.User;
import com.hu4java.user.service.UserService;
import com.hu4java.util.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 后台登录验证
 * @author chenzhenhu
 */
public class UserRealm extends AuthorizingRealm {

    @Lazy
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User currentUser = ShiroUtils.currentLogin();
        if (!CollectionUtils.isEmpty(currentUser.getRoleList())) {
            Set<String> roles = currentUser.getRoleList().stream().filter(role -> Objects.equals(role.getStatus(), Status.ENABLE.getStatus()))
                    .map(Role::getCode).collect(Collectors.toSet());
            simpleAuthorizationInfo.setRoles(roles);
        }

        if (!CollectionUtils.isEmpty(currentUser.getMenuList())) {
            Set<String> permissions = currentUser.getMenuList().stream()
                    .filter(menu -> Objects.equals(menu.getStatus(), Status.ENABLE.getStatus()) && StringUtils.isNotBlank(menu.getCode()))
                    .map(Menu::getCode).collect(Collectors.toSet());
            simpleAuthorizationInfo.setStringPermissions(permissions);
        }

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        User user = userService.getByUsername(username);
        if (null == user) {
            throw new UnknownAccountException(ResultCode.UNKNOWN_ACCOUNT.getMessage());
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), user.getUsername());
    }
}
