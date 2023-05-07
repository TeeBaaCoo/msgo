package com.zk.msgo.enums;

/**
 * 消息类型枚举类
 */
public enum MessageType {

    NOTICE(10, "通知类消息"),
    MARKETING(20, "营销类消息"),
    AUTH_CODE(30, "验证码消息");

    private Integer code;

    private String description;

    MessageType(Integer code, String description) {
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
