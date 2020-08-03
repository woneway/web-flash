package com.mtsearch.operation.security;

import com.mtsearch.operation.bean.constant.state.ManagerStatus;
import com.mtsearch.operation.bean.core.ShiroUser;
import com.mtsearch.operation.bean.entity.system.Role;
import com.mtsearch.operation.bean.entity.system.User;
import com.mtsearch.operation.bean.vo.SpringContextHolder;
import com.mtsearch.operation.cache.TokenCache;
import com.mtsearch.operation.dao.system.MenuRepository;
import com.mtsearch.operation.dao.system.RoleRepository;
import com.mtsearch.operation.dao.system.UserRepository;
import com.mtsearch.operation.service.system.impl.ConstantFactory;
import com.mtsearch.operation.utils.Convert;
import com.mtsearch.operation.utils.HttpUtil;
import com.mtsearch.operation.utils.StringUtil;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@DependsOn("springContextHolder")
@Transactional(readOnly = true)
public class ShiroFactroy {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TokenCache tokenCache;

    public static ShiroFactroy me() {
        return SpringContextHolder.getBean(ShiroFactroy.class);
    }


    public User user(String account) {

        User user = userRepository.findByAccount(account);

        // 账号不存在
        if (null == user) {
            throw new CredentialsException();
        }
        // 账号被冻结
        if (user.getStatus() != ManagerStatus.OK.getCode()) {
            throw new LockedAccountException();
        }
        return user;
    }


    public ShiroUser shiroUser(User user) {
        ShiroUser shiroUser = tokenCache.getUser(HttpUtil.getToken());
        if (shiroUser != null) {
            return shiroUser;
        }
        shiroUser = new ShiroUser();
        shiroUser.setId(Long.valueOf(user.getId()));            // 账号id
        shiroUser.setAccount(user.getAccount());// 账号
        shiroUser.setDeptId(user.getDeptid());    // 部门id
        shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptid()));// 部门名称
        shiroUser.setName(user.getName());        // 用户名称
        shiroUser.setPassword(user.getPassword());
        Long[] roleArray = Convert.toLongArray(",", user.getRoleid());
        List<Long> roleList = new ArrayList<Long>();
        List<String> roleNameList = new ArrayList<String>();
        List<String> roleCodeList = new ArrayList<String>();
        Set<String> permissions = new HashSet<String>();
        Set<String> resUrls = new HashSet<>();
        for (Long roleId : roleArray) {
            roleList.add(roleId);
            Role role = roleRepository.getOne(roleId);
            roleNameList.add(role.getName());
            roleCodeList.add(role.getTips());
            permissions.addAll(menuRepository.getResCodesByRoleId(roleId));
            List<String> list = menuRepository.getResUrlsByRoleId(roleId);
            for (String resUrl : list) {
                if (StringUtil.isNotEmpty(resUrl)) {
                    resUrls.add(resUrl);
                }
            }


        }
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);
        shiroUser.setRoleCodes(roleCodeList);
        shiroUser.setPermissions(permissions);

        shiroUser.setUrls(resUrls);
        tokenCache.setUser(HttpUtil.getToken(), shiroUser);
        return shiroUser;
    }

    public SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName) {
        String credentials = user.getPassword();
        // 密码加盐处理
        String source = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }

}
