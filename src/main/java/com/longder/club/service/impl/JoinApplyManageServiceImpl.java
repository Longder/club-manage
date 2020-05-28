package com.longder.club.service.impl;

import com.longder.club.entity.enums.ApplyStatus;
import com.longder.club.entity.po.JoinApply;
import com.longder.club.entity.po.SysUser;
import com.longder.club.repository.JoinApplyRepository;
import com.longder.club.repository.SysUserRepository;
import com.longder.club.security.SecurityUtil;
import com.longder.club.service.JoinApplyManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 入社申请管理业务Service
 */
@Service
public class JoinApplyManageServiceImpl implements JoinApplyManageService {

    @Resource
    private JoinApplyRepository joinApplyRepository;
    @Resource
    private SysUserRepository sysUserRepository;

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

    /**
     * 新增一个申请
     *
     * @param joinApply
     */
    @Override
    @Transactional
    public void addOneApply(JoinApply joinApply) {
        //当前用户
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        joinApply.setApplyUser(currentUser);
        joinApply.setApplyStatus(ApplyStatus.APPLYING);
        joinApplyRepository.save(joinApply);
    }

    /**
     * 查询获取一个申请
     *
     * @param applyId
     * @return
     */
    @Override
    public JoinApply getOneJoinApply(Long applyId) {
        return joinApplyRepository.getOne(applyId);
    }

    /**
     * 待审核列表
     *
     * @return
     */
    @Override
    public List<JoinApply> listApplying() {
        return joinApplyRepository.listApplying();
    }

    /**
     * 审核入社申请
     *
     * @param joinApply
     */
    @Override
    @Transactional
    public void approve(JoinApply joinApply) {
        JoinApply dbApply = joinApplyRepository.getOne(joinApply.getId());
        dbApply.setApplyStatus(joinApply.getApplyStatus());
        joinApplyRepository.save(dbApply);
        //如果同意，需要新增一个Club，把Club关联到该用户下
        if (dbApply.getApplyStatus() == ApplyStatus.CONSENT) {
            SysUser applyUser = dbApply.getApplyUser();
            applyUser.setClub(dbApply.getClub());
            sysUserRepository.save(applyUser);
        }
    }
}
