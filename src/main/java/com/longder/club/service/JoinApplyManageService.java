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
}
