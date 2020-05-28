package com.longder.club.service;

import com.longder.club.entity.po.ClubApply;

import java.util.List;

/**
 * 社团申请管理业务Service
 */
public interface ClubApplyManageService {

    /**
     * 检查是否可以申请社团
     *
     * @return true:可以 false:不可以
     */
    boolean canApply();

    /**
     * 新增一个社团申请
     *
     * @param clubApply
     */
    void addOneApply(ClubApply clubApply);

    /**
     * 查询我的申请（干事查看）
     *
     * @return
     */
    List<ClubApply> listMyApply();

    /**
     * 查询获取一个申请
     *
     * @param applyId
     * @return
     */
    ClubApply getOneApply(Long applyId);

    /**
     * 查询正在申请中的社团申请
     *
     * @return
     */
    List<ClubApply> listApplying();

    /**
     * 审核
     *
     * @param clubApply
     */
    void approve(ClubApply clubApply);
}
