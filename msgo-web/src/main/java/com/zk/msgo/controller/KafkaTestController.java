package com.zk.msgo.controller;

import com.zk.msgo.kafkaTest.UserLogProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaTestController {

    @Autowired
    private UserLogProducer userLogProducer;

    @RequestMapping(value = "/kafka/insert", method = RequestMethod.GET)
    public String insert(String userId) {
        userLogProducer.sendLog(userId);
        return null;
    }
}
