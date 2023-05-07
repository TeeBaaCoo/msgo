package com.zk.msgo.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.zk.msgo.dao.MessageTemplateDao;
import com.zk.msgo.domain.MessageTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageTemplateController {

    @Autowired
    private MessageTemplateDao messageTemplateDao;

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insert() {
        MessageTemplate messageTemplate = MessageTemplate.builder()
                .name("test短信")
                .auditStatus(10)
                .flowId("yyyy")
                .msgStatus(10)
                .idType(10)
                .sendChannel(10)
                .templateType(10)
                .msgType(10)
                .expectPushTime("0")
                .msgContent("333333")
                .sendAccount(66)
                .creator("zk")
                .updater("zk")
                .team("zk")
                .proposer("zk")
                .auditor("zk")
                .isDeleted(0)
                .created(Math.toIntExact(DateUtil.currentSeconds()))
                .updated(Math.toIntExact(DateUtil.currentSeconds()))
                .deduplicationTime(1)
                .isNightShield(0)
                .build();
        MessageTemplate info = messageTemplateDao.save(messageTemplate);
        return JSON.toJSONString(info);
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String query() {
        Iterable<MessageTemplate> all = messageTemplateDao.findAll();
        for (MessageTemplate messageTemplate : all) {
            return JSON.toJSONString(messageTemplate);
        }
        return null;
    }

}
