package com.longder.club.repository;

import com.longder.club.entity.po.ClubMatter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 社团事项表数据库操作
 */
public interface ClubMatterRepository extends JpaRepository<ClubMatter, Long> {

    /**
     * 根据社团id查询社团事项
     *
     * @param clubId
     * @return
     */
    @Query("SELECT M FROM ClubMatter M WHERE M.club.id = :clubId")
    List<ClubMatter> listByClubId(@Param("clubId") Long clubId);
}
