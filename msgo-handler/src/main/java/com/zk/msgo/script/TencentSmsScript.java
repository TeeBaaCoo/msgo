package com.zk.msgo.script;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20210111.models.SendStatus;
import com.zk.msgo.domain.SmsRecord;
import com.zk.msgo.enums.SmsStatus;
import com.zk.msgo.pojo.SmsParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zk
 * @since 2023/5/7
 * 接入腾讯云
 */
@Service
@Slf4j
@Configuration
public class TencentSmsScript implements SmsScript{

    private static final Integer PHONE_NUM = 11;
    private static final String URL = "sms.tencentcloudapi.com";
    private static final String REGION = "ap-guangzhou";

    @Value("${tencent.sms.account.secret_id}")
    private String SECRET_ID;

    @Value("${tencent.sms.account.secret_key}")
    private String SECRET_KEY;

    @Value("${tencent.sms.account.sms_sdk_app_id}")
    private String SMS_SDK_APP_ID;

    @Value("${tencent.sms.account.template_id}")
    private String TEMPLATE_ID;

    @Value("${tencent.sms.account.sign_name}")
    private String SIGN_NAME;

    @Override
    public List<SmsRecord> send(SmsParam smsParam) {

        try{
            SmsClient client = init();

            SendSmsRequest request = assembleRequest(smsParam);

            SendSmsResponse response = client.SendSms(request);

            return assembleSmsRecord(smsParam, response);
        } catch (TencentCloudSDKException e) {
            log.error("send tencent sms fail!{},params:{}",
                    Throwables.getStackTraceAsString(e), JSON.toJSONString(smsParam));
            return null;
        }
    }

    private List<SmsRecord> assembleSmsRecord(SmsParam smsParam, SendSmsResponse sendSmsResponse) {
        if (sendSmsResponse == null || ArrayUtil.isEmpty(sendSmsResponse.getSendStatusSet())) {
            return null;
        }
        List<SmsRecord> smsRecordList = new ArrayList<>();

        for (SendStatus sendStatus : sendSmsResponse.getSendStatusSet()) {
            String phone = new StringBuilder(new StringBuilder(sendStatus.getPhoneNumber())
                    .reverse().substring(0, PHONE_NUM)).reverse().toString();
            SmsRecord smsRecord = SmsRecord.builder()
                    .sendDate(Integer.valueOf(DateUtil.format(new Date(), "yyyyMMdd")))
                    .messageTemplateId(smsParam.getMessageTemplateId())
                    .phone(Long.valueOf(phone))
                    .supplierId(smsParam.getSupplierId())
                    .supplierName(smsParam.getSupplierName())
                    .seriesId(sendStatus.getSerialNo())
                    .chargingNum(Math.toIntExact(sendStatus.getFee()))
                    .status(SmsStatus.SEND_SUCCESS.getCode())
                    .reportContent(sendStatus.getCode())
                    .created(Math.toIntExact(DateUtil.currentSeconds()))
                    .updated(Math.toIntExact(DateUtil.currentSeconds()))
                    .build();

            smsRecordList.add(smsRecord);
        }
        return smsRecordList;
    }

    private SendSmsRequest assembleRequest(SmsParam smsParam) {
        SendSmsRequest req = new SendSmsRequest();
        String[] phoneNumberSet1 = smsParam.getPhones().toArray(new String[smsParam.getPhones().size() - 1]);
        String[] templateParamSet1 = {smsParam.getContent()};
        req.setPhoneNumberSet(phoneNumberSet1);
        req.setSmsSdkAppId(SMS_SDK_APP_ID);
        req.setSignName(SIGN_NAME);
        req.setTemplateId(TEMPLATE_ID);
        req.setTemplateParamSet(templateParamSet1);
        req.setSessionContext(IdUtil.fastSimpleUUID());
        return req;
    }

    private SmsClient init() {
        //初始化client
        Credential cred = new Credential(SECRET_ID, SECRET_KEY);
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(URL);
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        return new SmsClient(cred, REGION, clientProfile);
    }
}
