package com.longder.club.controller;

import com.longder.club.entity.po.ClubFlow;
import com.longder.club.entity.po.SysUser;
import com.longder.club.security.SecurityUtil;
import com.longder.club.service.ClubFlowManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/clubFlow")
public class ClubFlowManageController {

    @Resource
    private ClubFlowManageService clubFlowManageService;

    @GetMapping("/list")
    public String list(Model model){
        SysUser currentUser = SecurityUtil.getCurrentUser();
        //查看当前用户是否有管理的社团了
        assert currentUser != null;
        if (ObjectUtils.isEmpty(currentUser.getClub())) {
            model.addAttribute("canAdd", false);
        } else {
            model.addAttribute("canAdd", true);
            model.addAttribute("flowList", clubFlowManageService.listAllFlow());
        }
        return "/clubFlow/list";
    }

    /**
     * 去添加收支
     * @param model
     * @return
     */
    @GetMapping("/toAdd")
    public String toAdd(Model model){
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        model.addAttribute("clubName", currentUser.getClub().getName());
        return "/clubFlow/add-flow-modal";
    }

    /**
     * 添加收支
     * @param clubFlow
     * @return
     */
    @PostMapping("/add")
    public String add(ClubFlow clubFlow){
        clubFlowManageService.addOneClubFlow(clubFlow);
        return "redirect:/admin/clubFlow/list";
    }

    /**
     * 移除收支
     * @param flowId
     * @return
     */
    @GetMapping("/remove")
    public String remove(Long flowId){
        clubFlowManageService.removeOneClubFLow(flowId);
        return "redirect:/admin/clubFlow/list";
    }
}
