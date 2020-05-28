package com.longder.club.repository;

import com.longder.club.entity.po.JoinApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JoinApplyRepository extends JpaRepository<JoinApply, Long> {

    /**
     * 根据申请人id查询所有申请
     *
     * @param applyUserId
     * @return
     */
    @Query("SELECT A FROM JoinApply A WHERE A.applyUser.id = :applyUserId")
    List<JoinApply> listByApplyUserId(@Param("applyUserId") Long applyUserId);


    /**
     * 根据申请人id查询申请中和已通过的申请
     *
     * @param applyUserId
     * @return
     */
    @Query("SELECT A FROM JoinApply A WHERE A.applyUser.id = :applyUserId and (A.applyStatus = 'APPLYING' or A.applyStatus = 'CONSENT')")
    List<JoinApply> listApplyingAndConsentByApplyUserId(@Param("applyUserId") Long applyUserId);
}
