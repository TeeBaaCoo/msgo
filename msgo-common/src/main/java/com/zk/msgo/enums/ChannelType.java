package com.zk.msgo.enums;

/**
 * 渠道类型枚举类
 */
public enum ChannelType {

    IM(10, "IM(站内信)"),
    PUSH(20, "push(通知栏)"),
    SMS(30, "sms(短信)"),
    EMAIL(49, "email(邮件)"),
    OFFICIAL_ACCOUNT(50, "officialAccounts(服务号)"),
    MINI_PROGRAM(60, "miniProgram(小程序)");

    private Integer code;

    private String description;

    ChannelType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
