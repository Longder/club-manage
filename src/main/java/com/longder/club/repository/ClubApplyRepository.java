package com.longder.club.repository;

import com.longder.club.entity.po.ClubApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 建社申请表数据库操作
 */
public interface ClubApplyRepository extends JpaRepository<ClubApply, Long> {

    /**
     * 根据申请人id查询申请中和已通过的申请
     *
     * @param applyUserId
     * @return
     */
    @Query("SELECT A FROM ClubApply A WHERE A.applyUser.id = :applyUserId and (A.applyStatus = 'APPLYING' or A.applyStatus = 'CONSENT')")
    List<ClubApply> listApplyingAndConsentByApplyUserId(@Param("applyUserId") Long applyUserId);

    /**
     * 根据申请人id查询所有申请
     *
     * @param applyUserId
     * @return
     */
    @Query("SELECT A FROM ClubApply A WHERE A.applyUser.id = :applyUserId")
    List<ClubApply> listByApplyUserId(@Param("applyUserId") Long applyUserId);

    /**
     * 查询正在申请中的申请
     * @return
     */
    @Query("SELECT A FROM ClubApply A WHERE A.applyStatus = 'APPLYING'")
    List<ClubApply> listApplying();
}
