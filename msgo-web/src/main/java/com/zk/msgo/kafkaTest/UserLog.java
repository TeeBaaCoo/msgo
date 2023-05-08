package com.zk.msgo.kafkaTest;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 日志数据
 *
 * @author zk
 * @since 2023/5/7
 */
@Data
@Accessors(chain = true)
public class UserLog {

    private String username;

    private String userId;

    private String state;
}
