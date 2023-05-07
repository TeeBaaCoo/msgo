package com.zk.msgo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 全局响应状态
 *
 * @author zk
 * @since 2023/5/7
 */
@Getter
@ToString
@AllArgsConstructor
public enum RespStatusEnum {

    SUCCESS("msgo0000", "操作成功"),
    FAIL("msgo0001", "操作失败"),
    CLIENT_BAD_PARAMETERS("msgo0100", "客户端参数错误"),
    SERVICE_ERROR("msgo0101", "服务异常"),
    RESOURCE_NOT_FOUND("msgo0404", "资源不存在");

    private final String code;
    private final String msg;




}
