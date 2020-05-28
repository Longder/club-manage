package com.longder.club.controller;

import com.longder.club.entity.po.ClubApply;
import com.longder.club.entity.po.JoinApply;
import com.longder.club.entity.po.SysUser;
import com.longder.club.security.SecurityUtil;
import com.longder.club.service.ClubManageService;
import com.longder.club.service.JoinApplyManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        assert sysUser != null;
        model.addAttribute("realName",sysUser.getName());
        return "/joinApply/add-apply-modal";
    }


    /**
     * 新增一个申请
     * @param joinApply
     * @return
     */
    @PostMapping("/add")
    public String add(JoinApply joinApply){
        joinApplyManageService.addOneApply(joinApply);
        return "redirect:/admin/joinApply/listMyApply";
    }

    /**
     * 申请详情
     * @param applyId
     * @return
     */
    @GetMapping("/detail")
    public String detail(Long applyId,Model model){
        model.addAttribute("apply",joinApplyManageService.getOneJoinApply(applyId));
        return "/joinApply/apply-detail-modal";
    }

    /**
     * 待审核列表（社联管理员查看）
     *
     * @return
     */
    @GetMapping("/listApplying")
    public String listApplying(Model model) {
        model.addAttribute("applyList", joinApplyManageService.listApplying());
        return "/joinApply/list-applying";
    }


    /**
     * 去审核页
     *
     * @return
     */
    @GetMapping("/toApprove")
    public String toApprove(Long applyId, Model model) {
        model.addAttribute("apply", joinApplyManageService.getOneJoinApply(applyId));
        return "/joinApply/approve-modal";
    }

    /**
     * 审核入社申请
     *
     * @return
     */
    @PostMapping("/approve")
    public String approve(JoinApply joinApply) {
        joinApplyManageService.approve(joinApply);
        return "redirect:/admin/joinApply/listApplying";
    }


}
