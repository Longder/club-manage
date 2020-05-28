package com.longder.club.service.impl;

import com.longder.club.entity.po.ClubMatter;
import com.longder.club.entity.po.SysUser;
import com.longder.club.repository.ClubMatterRepository;
import com.longder.club.security.SecurityUtil;
import com.longder.club.service.ClubMatterManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 社团事项操作业务
 */
@Service
public class ClubMatterManageServiceImpl implements ClubMatterManageService {

    @Resource
    private ClubMatterRepository clubMatterRepository;

    /**
     * 查询所有的社团事项（当前用户管理的社团）
     *
     * @return
     */
    @Override
    public List<ClubMatter> listAllClubMatters() {
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        return clubMatterRepository.listByClubId(currentUser.getClub().getId());
    }

    /**
     * 新增一个社团事项
     *
     * @param clubMatter
     */
    @Override
    @Transactional
    public void addOneClubMatter(ClubMatter clubMatter) {
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        clubMatter.setClub(currentUser.getClub());
        //处理日期
        clubMatter.parseDate();
        clubMatterRepository.save(clubMatter);
    }

    /**
     * 查询获取一个社团事项
     *
     * @param matterId
     * @return
     */
    @Override
    public ClubMatter getOneClubMatter(Long matterId) {
        return clubMatterRepository.getOne(matterId);
    }

    /**
     * 删除一个社团事项
     *
     * @param matterId
     */
    @Override
    @Transactional
    public void removeOneClubMatter(Long matterId) {
        clubMatterRepository.deleteById(matterId);
    }
}
