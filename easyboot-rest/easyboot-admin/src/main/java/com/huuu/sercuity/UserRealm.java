package com.huuu.sercuity;

import com.huuu.base.enums.Status;
import com.huuu.base.result.ResultCode;
import com.huuu.system.entity.Menu;
import com.huuu.system.entity.Role;
import com.huuu.system.entity.User;
import com.huuu.system.service.MenuService;
import com.huuu.system.service.RoleService;
import com.huuu.system.service.UserService;
import com.huuu.util.ShiroUtils;
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

import java.util.List;
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
    @Lazy
    @Autowired
    private RoleService roleService;
    @Lazy
    @Autowired
    private MenuService menuService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User currentUser = ShiroUtils.currentLogin();

        List<Role> roleList = roleService.listByUserId(currentUser.getId());
        List<Menu> menuList = menuService.listByUserId(currentUser.getId());



        if (!CollectionUtils.isEmpty(roleList)) {
            Set<String> roles = roleList.stream().filter(role -> Objects.equals(role.getStatus(), Status.ENABLE.getStatus()))
                    .map(Role::getCode).collect(Collectors.toSet());
            simpleAuthorizationInfo.setRoles(roles);
        }

        if (!CollectionUtils.isEmpty(menuList)) {
            Set<String> permissions = menuList.stream()
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
