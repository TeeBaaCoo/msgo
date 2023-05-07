package com.zk.msgo.domain;

import com.zk.msgo.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.intellij.lang.annotations.Identifier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 消息模板DO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MessageTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //模板表体
    private String name;

    //审核状态
    private Integer auditStatus;

    //审核工单Id
    private String flowId;

    //消息状态
    private Integer msgStatus;

    //id类型
    private Integer idType;

    //渠道
    private Integer sendChannel;

    //模板类型
    private Integer templateType;

    //消息类型
    private Integer msgType;

    //推送时间: 0 = 立即发送; else = 表达式
    private String expectPushTime;

    //消息内容
    private String msgContent;

    //发送账号
    private Integer sendAccount;

    private String creator;

    private String updater;

    private String auditor;

    private String team;

    private String proposer;

    //是否删除: 0 = 已删除; 1 = 删除;
    private Integer isDeleted;

    private Integer created;

    private Integer updated;

    //去重时间
    private Integer deduplicationTime;

    //夜间屏蔽: 0 = false; 1 = true;
    private Integer isNightShield;
}
