package com.longder.club.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * 系统用户实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "SYS_USER")
@Proxy(lazy = false)
public class SysUser extends BaseIdEntity implements UserDetails {

    /**
     * 姓名
     */
    @Column(name = "name_")
    private String name;
    /**
     * 登录名
     */
    @Column(name = "login_name_")
    private String loginName;

    /**
     * 登陆密码
     */
    @Column(name = "password_")
    private String password;

    /**
     * 邮箱
     */
    @Column(name = "email_")
    private String email;

    /**
     * 电话
     */
    @Column(name = "phone_")
    private String phone;

    /**
     * 角色，不持久化，vo dto用
     */
    @Transient
    private SysRole role;

    /**
     * 关联社团
     */
    @ManyToOne
    @JoinColumn(name = "club_id_")
    private Club club;

    /**
     * 用户角色
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sysUser", cascade = CascadeType.ALL)
    private List<SysUserRole> roles;

    /**
     * 封装角色
     */
    public void fillRole(){
        this.role = this.roles.get(0).getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SysUser{");
        sb.append("name='").append(name).append('\'');
        sb.append(", loginName='").append(loginName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
