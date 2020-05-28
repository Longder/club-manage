package com.longder.club.repository;

import com.longder.club.entity.po.ClubFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 社团流水表管理
 */
public interface ClubFlowRepository extends JpaRepository<ClubFlow,Long> {

    /**
     * 根据社团Id查询流水
     * @param clubId
     * @return
     */
    @Query("SELECT F FROM ClubFlow F WHERE F.club.id = :clubId")
    List<ClubFlow> listByClubId(@Param("clubId") Long clubId);
}
