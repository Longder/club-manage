package com.longder.club.controller;

import com.longder.club.entity.po.SysUser;
import com.longder.club.service.UserManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 用户管理的控制器
 */
@Controller
@RequestMapping("/admin/user")
public class UserManageController {

    @Resource
    private UserManageService userManageService;
    /**
     * 用户列表
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("list",userManageService.listAllUser());
        return "user/list";
    }

    /**
     * 去编辑用户
     * @param userId
     * @return
     */
    @GetMapping("/toEdit")
    public String toEdit(Long userId,Model model){
        model.addAttribute("user",userManageService.getOneUser(userId));
        return "user/edit-user-modal";
    }

    /**
     * 编辑用户
     * @param sysUser
     * @return
     */
    @PostMapping("/edit")
    public String edit(SysUser sysUser){
        userManageService.editOneUser(sysUser);
        return "redirect:/admin/user/list";
    }
}
