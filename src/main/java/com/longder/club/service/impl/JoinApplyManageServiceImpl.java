package com.longder.club.service.impl;

import com.longder.club.entity.po.JoinApply;
import com.longder.club.entity.po.SysUser;
import com.longder.club.repository.JoinApplyRepository;
import com.longder.club.security.SecurityUtil;
import com.longder.club.service.JoinApplyManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 入社申请管理业务Service
 */
@Service
public class JoinApplyManageServiceImpl implements JoinApplyManageService {

    @Resource
    private JoinApplyRepository joinApplyRepository;

    /**
     * 查询我的申请
     *
     * @return
     */
    @Override
    public List<JoinApply> listMyApply() {
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        return joinApplyRepository.listByApplyUserId(currentUser.getId());
    }

    /**
     * 检查是否可以申请加入社团
     *
     * @return
     */
    @Override
    public boolean canApply() {
        //当前用户
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        List<JoinApply> joinApplyList = joinApplyRepository.listApplyingAndConsentByApplyUserId(currentUser.getId());
        return joinApplyList.size() == 0;
    }
}
