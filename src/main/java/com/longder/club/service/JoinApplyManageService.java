package com.longder.club.service;

import com.longder.club.entity.po.JoinApply;

import java.util.List;

/**
 * 入社申请管理业务Service
 */
public interface JoinApplyManageService {

    /**
     * 查询我的申请
     *
     * @return
     */
    List<JoinApply> listMyApply();

    /**
     * 检查是否可以申请加入社团
     *
     * @return
     */
    boolean canApply();

    /**
     * 新增一个申请
     * @param joinApply
     */
    void addOneApply(JoinApply joinApply);

    /**
     * 查询获取一个申请
     * @param applyId
     * @return
     */
    JoinApply getOneJoinApply(Long applyId);

    /**
     * 待审核列表
     * @return
     */
    List<JoinApply> listApplying();

    /**
     * 审核入社申请
     * @param joinApply
     */
    void approve(JoinApply joinApply);
}
