package com.longder.club.service.impl;

import com.longder.club.entity.po.SysRole;
import com.longder.club.entity.po.SysUser;
import com.longder.club.entity.po.SysUserRole;
import com.longder.club.repository.SysUserRepository;
import com.longder.club.repository.SysUserRoleRepository;
import com.longder.club.security.SecurityUtil;
import com.longder.club.service.UserManageService;
import com.longder.club.util.EncryptionUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理业务
 */
@Service
public class UserManageServiceImpl implements UserManageService {

    @Resource
    private SysUserRepository sysUserRepository;
    @Resource
    private SysUserRoleRepository sysUserRoleRepository;

    /**
     * 新增保存一个用户
     *
     * @param sysUser
     * @param role
     */
    @Override
    @Transactional
    public void saveOneUser(SysUser sysUser, SysRole role) {
        if (ObjectUtils.isEmpty(sysUser.getId())) {//空的 新增
            //处理下密码
            sysUser.setPassword(EncryptionUtil.encrypt(sysUser.getPassword().trim()));
            //默认是有效的
            sysUserRepository.save(sysUser);
            SysUserRole userRole = new SysUserRole(sysUser, role);
            sysUserRoleRepository.save(userRole);
        }
    }

    /**
     * 检查登录名
     *
     * @param loginName 登录名
     * @return true:可以注册  false：不能注册
     */
    @Override
    public Boolean checkLoginName(String loginName) {
        SysUser sysUser = sysUserRepository.getByLoginName(loginName);
        return ObjectUtils.isEmpty(sysUser);
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<SysUser> listAllUser() {
        List<SysUser> sysUserList = sysUserRepository.findAll();
        //封装角色
        sysUserList.forEach(sysUser -> sysUser.setRole(sysUser.getRoles().get(0).getRole()));
        return sysUserList;
    }

    /**
     * 获取一个用户
     *
     * @param userId
     * @return
     */
    @Override
    public SysUser getOneUser(Long userId) {
        SysUser user = sysUserRepository.getOne(userId);
        //封装角色
        user.setRole(user.getRoles().get(0).getRole());
        return user;
    }

    /**
     * 更新一个用户
     *
     * @param sysUser
     */
    @Override
    @Transactional
    public void editOneUser(SysUser sysUser) {
        SysUser dbUser = sysUserRepository.getOne(sysUser.getId());
        dbUser.setName(sysUser.getName());
        dbUser.setPhone(sysUser.getPhone());
        dbUser.setEmail(sysUser.getEmail());
        sysUserRepository.save(dbUser);
    }

    /**
     * 修改密码
     *
     * @param newPassword
     */
    @Override
    public void changePassword(String newPassword) {
        SysUser sysUser = SecurityUtil.getCurrentUser();
        assert sysUser != null;
        sysUser.setPassword(EncryptionUtil.encrypt(newPassword.trim()));
        sysUserRepository.save(sysUser);
    }
}
