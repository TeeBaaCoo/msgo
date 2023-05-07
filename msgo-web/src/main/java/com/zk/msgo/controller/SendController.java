package com.zk.msgo.controller;

import com.zk.msgo.handler.SmsHandler;
import com.zk.msgo.pojo.TaskInfo;
import com.zk.msgo.pojo.vo.BasicResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashSet;

@RestController
public class SendController {

    @Autowired
    private SmsHandler smsHandler;

    @RequestMapping(value = "/smsSend", method = RequestMethod.GET)
    public BasicResultVO<Void> sendSms(String phone, String content, Long messageTemplateId) {
        TaskInfo taskInfo = TaskInfo.builder().receiver(new HashSet<>(
                Collections.singletonList(phone)))
                .content(content)
                .messageTemplateId(messageTemplateId)
                .build();
        if (smsHandler.doHandler(taskInfo)) {
            return BasicResultVO.success();
        }
        return BasicResultVO.fail();
    }
}
