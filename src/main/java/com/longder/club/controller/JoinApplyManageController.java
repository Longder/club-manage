package com.longder.club.controller;

import com.longder.club.entity.po.SysUser;
import com.longder.club.security.SecurityUtil;
import com.longder.club.service.ClubManageService;
import com.longder.club.service.JoinApplyManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 入社申请管理的控制器
 */
@Controller
@RequestMapping("/admin/joinApply")
public class JoinApplyManageController {

    @Resource
    private JoinApplyManageService joinApplyManageService;
    @Resource
    private ClubManageService clubManageService;

    /**
     * 我的申请列表
     *
     * @return
     */
    @GetMapping("/listMyApply")
    public String listMyApply(Model model) {
        model.addAttribute("applyList", joinApplyManageService.listMyApply());
        model.addAttribute("canApply", joinApplyManageService.canApply());
        return "/joinApply/list-my-apply";
    }

    /**
     * 去新增入社申请
     *
     * @param model
     * @return
     */
    @GetMapping("/toAdd")
    public String toAdd(Model model) {
        model.addAttribute("clubs", clubManageService.listAllClub());
        //当前用户
        SysUser sysUser = SecurityUtil.getCurrentUser();
//        model.addAttribute("realName",)
        return "/joinApply/add-apply-modal";
    }


}
