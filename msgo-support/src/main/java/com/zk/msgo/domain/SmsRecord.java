package com.zk.msgo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 短信回执和发送记录
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SmsRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long messageTemplateId;

    private Long phone;

    private Integer supplierId;

    private String supplierName;

    private String seriesId;

    private Integer chargingNum;

    private String reportContent;

    private Integer status;

    private Integer sendDate;

    private Integer created;

    private Integer updated;
}
