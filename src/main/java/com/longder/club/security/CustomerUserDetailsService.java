package com.longder.club.security;


import com.longder.club.entity.po.SysUser;
import com.longder.club.entity.po.SysUserRole;
import com.longder.club.repository.SysUserRepository;
import com.longder.club.repository.SysUserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;


/**
 * 自定义的SpringSecurity用来做用户认证的service
 */
@Component
@Slf4j
public class CustomerUserDetailsService implements UserDetailsService {

    @Resource
    private SysUserRepository sysUserRepository;
    @Resource
    private SysUserRoleRepository sysUserRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.debug("进入自定义的UserDetails");
        log.debug(s);
        SysUser sysUser = sysUserRepository.getByLoginName(s);
        if (ObjectUtils.isEmpty(sysUser)) {
            throw new BadCredentialsException("用户名不正确");
        }
        //设置权限进去
        List<SysUserRole> roleList = sysUserRoleRepository.listRolesByUserId(sysUser.getId());
        sysUser.setRoles(roleList);
        return sysUser;
    }
}
