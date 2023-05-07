package com.zk.msgo.enums;

public enum TemplateType {

    OPERATION(10, "运营类"),
    TECHNOLOGY(20, "技术类");

    private Integer code;

    private String description;

    TemplateType(Integer code, String description) {
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
