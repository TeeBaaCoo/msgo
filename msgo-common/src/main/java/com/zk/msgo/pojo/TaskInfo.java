package com.zk.msgo.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * 发送任务
 */
@Data
@Builder
public class TaskInfo {

    //消息模板Id
    private Long messageTemplateId;

    //业务Id
    private Long businessId;

    //接收方
    private Set<String> receiver;

    //渠道
    private Integer sendChannel;

    //Id类型
    private Integer idType;

    //模板类型
    private Integer templateType;

    //消息类型
    private Integer msgType;

    //消息内容
    private String content;

    //发送账号
    private Integer sendAccount;

    //去重时间
    private Integer deduplicationTime;

    //夜间屏蔽: 0 = false; 1 = true
    private Integer isNightShield;



}
