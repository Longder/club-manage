package com.longder.club.controller;

import com.longder.club.entity.po.ClubApply;
import com.longder.club.service.ClubApplyManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 社团申请管理控制器
 */
@Controller
@RequestMapping("/admin/clubApply")
public class ClubApplyManageController {

    @Resource
    private ClubApplyManageService clubApplyManageService;

    /**
     * 我的社团申请列表（社团干事查看）
     *
     * @return
     */
    @GetMapping("/listMyApply")
    public String listMyApply(Model model) {
        //检查是否可以申请
        model.addAttribute("canApply", clubApplyManageService.canApply());
        model.addAttribute("applyList", clubApplyManageService.listMyApply());
        return "clubApply/list-my-apply";
    }

    /**
     * 去添加社团申请
     *
     * @return
     */
    @GetMapping("/toAdd")
    public String toAdd() {
        return "clubApply/add-apply-modal";
    }

    /**
     * 新增社团申请
     *
     * @return
     */
    @PostMapping("/add")
    public String add(ClubApply clubApply) {
        clubApplyManageService.addOneApply(clubApply);
        return "redirect:/admin/clubApply/listMyApply";
    }

    /**
     * 详情
     *
     * @param applyId
     * @return
     */
    @GetMapping("/detail")
    public String detail(Long applyId, Model model) {
        model.addAttribute("apply", clubApplyManageService.getOneApply(applyId));
        return "clubApply/apply-detail-modal";
    }

    /**
     * 待审核列表（社联管理员查看）
     * @return
     */
    @GetMapping("/listApplying")
    public String listApplying(Model model){
        model.addAttribute("applyList",clubApplyManageService.listApplying());
        return "clubApply/list-applying";
    }

    /**
     * 去审核页
     * @return
     */
    @GetMapping("/toApprove")
    public String toApprove(Long applyId, Model model){
        model.addAttribute("apply", clubApplyManageService.getOneApply(applyId));
        return "clubApply/approve-modal";
    }
}
