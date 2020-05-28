package com.longder.club.service.impl;

import com.longder.club.entity.po.Club;
import com.longder.club.repository.ClubRepository;
import com.longder.club.service.ClubManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 社团管理的业务
 */
@Service
public class ClubManageServiceImpl implements ClubManageService {
    @Resource
    private ClubRepository clubRepository;

    /**
     * 查询所有社团
     *
     * @return
     */
    @Override
    public List<Club> listAllClub() {
        return clubRepository.findAll();
    }
}
