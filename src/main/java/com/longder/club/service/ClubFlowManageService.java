package com.longder.club.service;

import com.longder.club.entity.po.ClubFlow;

import java.util.List;

/**
 * 社团流水管理的业务
 */
public interface ClubFlowManageService {

    /**
     * 查询所有社团流水
     *
     * @return
     */
    List<ClubFlow> listAllFlow();

    /**
     * 添加一个社团流水
     *
     * @param clubFlow
     */
    void addOneClubFlow(ClubFlow clubFlow);

    /**
     * 移除一个社团流水
     *
     * @param flowId
     */
    void removeOneClubFLow(Long flowId);
}
