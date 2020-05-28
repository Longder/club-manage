package com.longder.club.entity.po;

import com.longder.club.entity.enums.ApplyStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 加入社团的申请
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "JOIN_APPLY")
@Data
public class JoinApply extends BaseIdEntity {
    /**
     * 申请原因
     */
    private String reason;

    /**
     * 要申请加入的社团
     */
    @ManyToOne
    @JoinColumn(name = "club_id_")
    private Club club;

    /**
     * 申请人
     */
    @ManyToOne
    @JoinColumn(name = "apply_user_id_")
    private SysUser applyUser;

    /**
     * 申请状态
     */
    @Column(name = "apply_status_")
    private ApplyStatus applyStatus;
}
