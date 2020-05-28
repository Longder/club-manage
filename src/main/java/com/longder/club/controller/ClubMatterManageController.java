package com.longder.club.controller;

import com.longder.club.entity.po.ClubMatter;
import com.longder.club.entity.po.SysUser;
import com.longder.club.security.SecurityUtil;
import com.longder.club.service.ClubMatterManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 社团事项管理控制器
 */
@Controller
@RequestMapping("/admin/clubMatter")
public class ClubMatterManageController {

    @Resource
    private ClubMatterManageService clubMatterManageService;

    /**
     * 社团事项列表，查询自己所管理的社团的
     *
     * @return
     */
    @GetMapping("/list")
    public String list(Model model) {
        SysUser currentUser = SecurityUtil.getCurrentUser();
        //查看当前用户是否有管理的社团了
        assert currentUser != null;
        if (ObjectUtils.isEmpty(currentUser.getClub())) {
            model.addAttribute("canAdd", false);
        } else {
            model.addAttribute("canAdd", true);
            model.addAttribute("matterList", clubMatterManageService.listAllClubMatters());
        }
        return "/clubMatter/list";
    }

    /**
     * 去添加事项
     *
     * @param model
     * @return
     */
    @GetMapping("/toAdd")
    public String toAdd(Model model) {
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        model.addAttribute("clubName", currentUser.getClub().getName());
        return "/clubMatter/add-matter-modal";
    }

    /**
     * 添加事项
     *
     * @param clubMatter
     * @return
     */
    @PostMapping("/add")
    public String add(ClubMatter clubMatter) {
        clubMatterManageService.addOneClubMatter(clubMatter);
        return "redirect:/admin/clubMatter/list";
    }

    /**
     * 移除事项
     * @param matterId
     * @return
     */
    @GetMapping("/remove")
    public String remove(Long matterId){
        clubMatterManageService.removeOneClubMatter(matterId);
        return "redirect:/admin/clubMatter/list";
    }
}
