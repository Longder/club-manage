package com.longder.club.service;

import com.longder.club.entity.po.Club;

import java.util.List;

/**
 * 社团管理的业务
 */
public interface ClubManageService {

    /**
     * 查询所有社团
     * @return
     */
    List<Club> listAllClub();
}
