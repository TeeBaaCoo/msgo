package com.zk.msgo.enums;

/**
 * 消息状态枚举类
 */
public enum SmsStatus {

    SEND_SUCCESS(10, "调用渠道接口发送成功"),
    RECEIVE_SUCCESS(20, "用户收到短信(渠道回执状态成功)"),
    RECEIVE_FAIL(30, "用户未收到短信(渠道回执状态失败)");

    private Integer code;

    private String description;

    SmsStatus(Integer code, String description) {
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
