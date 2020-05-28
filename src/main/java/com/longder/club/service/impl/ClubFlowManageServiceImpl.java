package com.longder.club.service.impl;


import com.longder.club.entity.po.ClubFlow;
import com.longder.club.entity.po.SysUser;
import com.longder.club.repository.ClubFlowRepository;
import com.longder.club.security.SecurityUtil;
import com.longder.club.service.ClubFlowManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClubFlowManageServiceImpl implements ClubFlowManageService {

    @Resource
    private ClubFlowRepository clubFlowRepository;
    /**
     * 查询社团下所有流水
     *
     * @return
     */
    @Override
    public List<ClubFlow> listAllFlow() {
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        return clubFlowRepository.listByClubId(currentUser.getClub().getId());
    }

    /**
     * 添加一个社团流水
     *
     * @param clubFlow
     */
    @Override
    @Transactional
    public void addOneClubFlow(ClubFlow clubFlow) {
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        clubFlow.setClub(currentUser.getClub());
        //处理日期
        clubFlow.parseDate();
        clubFlowRepository.save(clubFlow);
    }

    /**
     * 移除一个社团流水
     *
     * @param flowId
     */
    @Override
    @Transactional
    public void removeOneClubFLow(Long flowId) {
        clubFlowRepository.deleteById(flowId);
    }
}
