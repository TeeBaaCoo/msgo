package com.zk.msgo.script;

import com.zk.msgo.domain.SmsRecord;
import com.zk.msgo.pojo.SmsParam;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SmsScript {

    /**
     * @param smsParam
     * @return
     */
    List<SmsRecord> send(SmsParam smsParam);
}
