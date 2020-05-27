package com.longder.club.service;

import com.longder.club.entity.po.SysRole;
import com.longder.club.entity.po.SysUser;

import java.util.List;

/**
 * 用户管理业务
 */
public interface UserManageService {
    /**
     * 新增保存一个用户
     * @param sysUser
     * @param role
     */
    void saveOneUser(SysUser sysUser, SysRole role);

    /**
     * 检查登录名
     * @param loginName
     * @return true:可以注册  false：不能注册
     */
    Boolean checkLoginName(String loginName);

    /**
     * 查询所有用户
     * @return
     */
    List<SysUser> listAllUser();

    /**
     * 获取一个用户
     * @param userId
     * @return
     */
    SysUser getOneUser(Long userId);

    /**
     * 更新一个用户
     * @param sysUser
     */
    void editOneUser(SysUser sysUser);

}
