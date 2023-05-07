package com.zk.msgo.script;

import com.zk.msgo.domain.SmsRecord;
import com.zk.msgo.pojo.SmsParam;

import java.util.List;

public interface SmsScript {

    /**
     * @param smsParam smsParam
     * @return list
     */
    List<SmsRecord> send(SmsParam smsParam);
}
