package com.zk.msgo.handler;

import cn.hutool.core.collection.CollUtil;
import com.tencentcloudapi.tci.v20190318.models.LightStandard;
import com.zk.msgo.dao.SmsRecordDao;
import com.zk.msgo.domain.SmsRecord;
import com.zk.msgo.pojo.SmsParam;
import com.zk.msgo.pojo.TaskInfo;
import com.zk.msgo.script.SmsScript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SmsHandler implements Handler{

    @Autowired
    private SmsRecordDao smsRecordDao;

    @Autowired
    private SmsScript smsScript;

    @Override
    public boolean doHandler(TaskInfo taskInfo) {
        SmsParam smsParam = SmsParam.builder()
                .phones(taskInfo.getReceiver())
                .content(taskInfo.getContent())
                .messageTemplateId(taskInfo.getMessageTemplateId())
                .supplierId(10)
                .supplierName("腾讯云通知类消息渠道")
                .build();
        List<SmsRecord> recordList = smsScript.send(smsParam);

        if (CollUtil.isEmpty(recordList)) {
            return false;
        }
        smsRecordDao.saveAll(recordList);
        return true;
    }
}
