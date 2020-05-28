package com.longder.club.service.impl;

import com.longder.club.entity.enums.ApplyStatus;
import com.longder.club.entity.po.Club;
import com.longder.club.entity.po.ClubApply;
import com.longder.club.entity.po.SysUser;
import com.longder.club.repository.ClubApplyRepository;
import com.longder.club.repository.ClubRepository;
import com.longder.club.repository.SysUserRepository;
import com.longder.club.security.SecurityUtil;
import com.longder.club.service.ClubApplyManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 社团申请管理业务Service
 */
@Service
public class ClubApplyManageServiceImpl implements ClubApplyManageService {

    @Resource
    private ClubApplyRepository clubApplyRepository;
    @Resource
    private ClubRepository clubRepository;
    @Resource
    private SysUserRepository sysUserRepository;

    /**
     * 检查是否可以申请社团
     *
     * @return true:可以 false:不可以
     */
    @Override
    public boolean canApply() {
        //当前用户
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        List<ClubApply> clubApplyList = clubApplyRepository.listApplyingAndConsentByApplyUserId(currentUser.getId());
        return clubApplyList.size() == 0;
    }

    /**
     * 新增一个社团申请
     *
     * @param clubApply
     */
    @Override
    @Transactional
    public void addOneApply(ClubApply clubApply) {
        SysUser currentUser = SecurityUtil.getCurrentUser();
        clubApply.setApplyUser(currentUser);
        clubApply.setApplyStatus(ApplyStatus.APPLYING);
        clubApplyRepository.save(clubApply);
    }

    /**
     * 查询我的申请（干事查看）
     *
     * @return
     */
    @Override
    public List<ClubApply> listMyApply() {
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        return clubApplyRepository.listByApplyUserId(currentUser.getId());
    }

    /**
     * 查询获取一个申请
     *
     * @param applyId
     * @return
     */
    @Override
    public ClubApply getOneApply(Long applyId) {
        return clubApplyRepository.getOne(applyId);
    }

    /**
     * 查询正在申请中的社团申请
     *
     * @return
     */
    @Override
    public List<ClubApply> listApplying() {
        return clubApplyRepository.listApplying();
    }

    /**
     * 审核
     *
     * @param clubApply
     */
    @Override
    @Transactional
    public void approve(ClubApply clubApply) {
        ClubApply dbApply = clubApplyRepository.getOne(clubApply.getId());
        dbApply.setApplyStatus(clubApply.getApplyStatus());
        clubApplyRepository.save(dbApply);
        //如果同意，需要新增一个Club，并记录更新社团干事关联的社团
        if (dbApply.getApplyStatus() == ApplyStatus.CONSENT) {
            Club club = new Club();
            club.setName(dbApply.getName());
            club.setDescription(dbApply.getDescription());
            clubRepository.save(club);
            dbApply.getApplyUser().setClub(club);
            sysUserRepository.save(dbApply.getApplyUser());
        }
    }
}
