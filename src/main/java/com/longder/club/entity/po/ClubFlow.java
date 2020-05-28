package com.longder.club.entity.po;

import com.longder.club.entity.enums.FlowType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 社团资金流水实体
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CLUB_FLOW")
@Data
public class ClubFlow extends BaseIdEntity {

    /**
     * 收支类型，区分收入还是支出
     */
    @Column(name = "flow_type_")
    private FlowType flowType;

    /**
     * 金额
     */
    @Column(name = "amount_")
    private BigDecimal amount;

    /**
     * 日期
     */
    @Column(name = "flow_date_")
    private LocalDate flowDate;

    /**
     * 备注
     */
    @Column(name = "remark_")
    private String remark;

    /**
     * 所属社团
     */
    @ManyToOne
    @JoinColumn(name = "club_id_")
    private Club club;

    /**
     * 日期字符串
     */
    @Transient
    private String flowDateStr;

    /**
     * 转换字符串到日期
     */
    public void parseDate(){
        this.flowDate = LocalDate.parse(flowDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
