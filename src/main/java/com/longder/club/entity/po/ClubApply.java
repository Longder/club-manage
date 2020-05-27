package com.longder.club.entity.po;

import com.longder.club.entity.enums.ApplyStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 创建社团的申请
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "CLUB_APPLY")
public class ClubApply extends BaseIdEntity {
    /**
     * 社团名称
     */
    @Column(name = "name_")
    private String name;

    /**
     * 社团描述
     */
    @Column(name = "description_")
    private String description;

    /**
     * 申请原因
     */
    @Column(name = "reason_")
    private String reason;

    /**
     * 申请状态
     */
    @Column(name = "apply_status_")
    private ApplyStatus applyStatus;

    /**
     * 申请人
     */
    @ManyToOne
    @JoinColumn(name = "user_id_")
    private SysUser applyUser;
}
