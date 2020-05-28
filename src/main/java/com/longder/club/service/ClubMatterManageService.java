package com.longder.club.service;

import com.longder.club.entity.po.ClubMatter;

import java.util.List;

/**
 * 社团事项操作业务
 */
public interface ClubMatterManageService {

    /**
     * 查询所有的社团事项
     *
     * @return
     */
    List<ClubMatter> listAllClubMatters();

    /**
     * 新增一个社团事项
     *
     * @param clubMatter
     */
    void addOneClubMatter(ClubMatter clubMatter);

    /**
     * 查询获取一个社团事项
     *
     * @param matterId
     * @return
     */
    ClubMatter getOneClubMatter(Long matterId);

    /**
     * 删除一个社团事项
     * @param matterId
     */
    void removeOneClubMatter(Long matterId);
}
