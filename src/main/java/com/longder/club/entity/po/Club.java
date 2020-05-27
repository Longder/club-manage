package com.longder.club.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 社团实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "CLUB")
public class Club extends BaseIdEntity{
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
}
