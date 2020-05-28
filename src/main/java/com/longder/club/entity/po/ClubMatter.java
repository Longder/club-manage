package com.longder.club.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 社团事项
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "CLUB_MATTER")
public class ClubMatter extends BaseIdEntity {

    /**
     * 事项名称
     */
    @Column(name = "name_")
    private String name;

    /**
     * 事项内容
     */
    @Column(name = "content_")
    private String content;

    /**
     * 事项日期
     */
    @Column(name = "matter_date_")
    private LocalDate matterDate;

    /**
     * 所属社团
     */
    @ManyToOne
    @JoinColumn(name = "club_id_")
    private Club club;

    /**
     * 日期字符串，不持久化
     */
    @Transient
    private String matterDateStr;

    /**
     * 解析转换日期
     */
    public void parseDate(){
        this.matterDate = LocalDate.parse(matterDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
