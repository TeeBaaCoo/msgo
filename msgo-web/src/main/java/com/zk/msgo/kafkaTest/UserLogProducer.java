package com.zk.msgo.kafkaTest;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * 生产者
 *
 * @author zk
 * @since 2023/5/7
 */
@Component
public class UserLogProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendLog(String userId) {
        UserLog userLog = new UserLog();
        userLog.setUsername("jhp").setUserId(userId).setState("0");
        System.err.println("发送日志:" + userLog);
        kafkaTemplate.send("msgo", JSON.toJSONString(userLog));
    }
}
