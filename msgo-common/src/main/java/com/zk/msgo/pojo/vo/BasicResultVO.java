package com.zk.msgo.pojo.vo;

import com.zk.msgo.constant.RespStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zk
 * @since 2023/5/7
 */
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public final class BasicResultVO<T> {

    private String code;

    private String msg;

    private T data;

    public BasicResultVO(RespStatusEnum status) {
        this(status, null);
    }

    public BasicResultVO(RespStatusEnum status, T data) {
        this(status, status.getMsg(), data);
    }

    public BasicResultVO(RespStatusEnum status, String msg, T data) {
        this.code = status.getCode();
        this.msg = msg;
        this.data = data;
    }

    /**
     * 默认响应
     */
    public static BasicResultVO<Void> success() {
        return new BasicResultVO<>(RespStatusEnum.SUCCESS);
    }

    /**
     * 自定义消息成功响应
     */
    public static <T> BasicResultVO<T> success(String msg) {
        return new BasicResultVO<>(RespStatusEnum.SUCCESS, msg, null);
    }

    /**
     * 带数据的成功响应
     */
    public static <T> BasicResultVO<T> success(T data) {
        return new BasicResultVO<>(RespStatusEnum.SUCCESS, data);
    }

    /**
     * 默认失败
     */
    public static <T> BasicResultVO<T> fail() {
        return new BasicResultVO<>(RespStatusEnum.FAIL, RespStatusEnum.FAIL.getMsg(), null);
    }

    /**
     * 自定义错误消息失败响应
     */
    public static <T> BasicResultVO<T> fail(String msg) {
        return fail(RespStatusEnum.FAIL, msg);
    }

    /**
     * 自定义状态失败响应
     */
    public static <T> BasicResultVO<T> fail(RespStatusEnum status) {
        return fail(status, status.getMsg());
    }

    /**
     * 自定义状态和消息失败响应
     */
    public static <T> BasicResultVO<T> fail(RespStatusEnum status, String msg) {
        return new BasicResultVO<>(status, msg, null);
    }
}
