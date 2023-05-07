package com.zk.msgo.dao;

import com.zk.msgo.domain.SmsRecord;
import org.springframework.data.repository.CrudRepository;

public interface SmsRecordDao extends CrudRepository<SmsRecord, Long> {
}
