package com.zk.msgo.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * @author zk
 * @since 2023/5/7
 * 发送短信参数
 */
@Data
@Builder
public class SmsParam {

    //业务Id
    private Long messageTemplateId;

    //渠道商Id
    private Integer supplierId;

    //渠道商名字
    private String supplierName;

    //发送的手机号
    private Set<String> phones;

    //短信内容
    private String content;
}
