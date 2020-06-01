package com.longder.club.controller;


import com.longder.club.entity.po.SysUser;
import com.longder.club.security.SecurityUtil;
import com.longder.club.service.UserManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 主控制器
 */
@Controller
@Slf4j
public class MainController {

    @Resource
    private UserManageService userManageService;

    /**
     * 主页默认跳转到登陆页
     *
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "redirect:toLogin";
    }

    /**
     * 去登陆页
     *
     * @return
     */
    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
    }


    /**
     * 去注册页面
     *
     * @return
     */
    @GetMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    /**
     * 检查登录名
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/checkLoginName")
    public Boolean checkLoginName(String loginName) {
        return userManageService.checkLoginName(loginName);
    }

    /**
     * 注册一个用户
     *
     * @param sysUser
     * @return
     */
    @PostMapping("/register")
    public String register(SysUser sysUser) {
        log.debug("开始注册用户，登录名：{}", sysUser.getLoginName());
        userManageService.saveOneUser(sysUser, sysUser.getRole());
        return "redirect:toLogin";
    }

    /**
     * 后台主页,登陆后访问的首页
     *
     * @return
     */
    @GetMapping("/admin/index")
    public String adminIndex(Model model) {
        //当前用户
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        model.addAttribute("userName", currentUser.getName());
        return "index";
    }

    /**
     * 仪表盘，欢迎页面
     *
     * @return
     */
    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("currentUser", SecurityUtil.getCurrentUser());
        return "dashboard";
    }

    /**
     * 管理员修改密码
     *
     * @return
     */
    @GetMapping("/admin/toEditPassword")
    public String toEditPassword() {
        return "user/edit-password-modal";
    }

}
