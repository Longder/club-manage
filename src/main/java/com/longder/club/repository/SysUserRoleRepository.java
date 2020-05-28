package com.longder.club.repository;


import com.longder.club.entity.po.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 用户角色表数据库操作
 */
public interface SysUserRoleRepository extends JpaRepository<SysUserRole, Long> {

    @Query("select ur from SysUserRole ur where ur.sysUser.id = :userId")
    List<SysUserRole> listRolesByUserId(@Param("userId") Long userId);
}
