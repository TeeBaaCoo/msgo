package com.zk.msgo.utils;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSON;
import com.google.common.base.Throwables;
import com.zk.msgo.config.OkHttpConfiguration;
import lombok.Data;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author zk
 * @date 2023/5/7
 */
@Data
@Component
public class OkHttpUtils {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType XML = MediaType.parse("application/json; charset=utf-8");

    private static final Logger logger = LoggerFactory.getLogger(OkHttpUtils.class);

    @Autowired
    private OkHttpClient okHttpClient;

    /**
     * get 请求
     *
     * @param url
     * @return string
     */
    public String doGet(String url) {
        return doGet(url, null, null);
    }

    /**
     * get 请求
     *
     * @param url
     * @param params
     * @return string
     */
    public String doGet(String url, Map<String, String> params) {
        return doGet(url, params, null);
    }
    /**
     * get 请求
     *
     * @param url
     * @param headers
     * @return string
     */
    public String doGetWithHeaders(String url, Map<String, String> headers) {
        return doGet(url, null, headers);
    }

    /**
     * get 请求
     *
     * @param url
     * @param params
     * @param headers
     * @return string
     */
    public String doGet(String url, Map<String, String> params, Map<String, String> headers) {
        StringBuilder sb = new StringBuilder(url);
        if (params != null && params.keySet().size() > 0) {
            boolean firstFlag = true;
            for (String key : params.keySet()) {
                if (firstFlag) {
                    sb.append("?").append(key).append("=").append(params.get(key));
                    firstFlag = false;
                } else {
                    sb.append("&").append(key).append("=").append(params.get(key));
                }
            }
        }
        Request.Builder builder = getBuilderWithHeaders(headers);
        Request request = builder.url(sb.toString()).build();
        logger.info("do get request and url[{}]", sb.toString());

        return execute(request);
    }

    /**
     * post 请求
     *
     * @param url
     * @param params
     * @param headers
     * @return String
     */
    public String doPost(String url, Map<String, String> params, Map<String, String> headers) {
        FormBody.Builder formBuilder = new FormBody.Builder();

        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                formBuilder.add(key, params.get(key));
            }
        }
        Request.Builder builder = getBuilderWithHeaders(headers);
        Request request = builder.url(url).post(formBuilder.build()).build();
        logger.info("do post request and url[{}]", url);

        return execute(request);
    }

    /**
     * 获取request Builder
     *
     * @param headers
     * @return
     */
    private  Request.Builder getBuilderWithHeaders(Map<String, String> headers) {
        Request.Builder builder = new Request.Builder();
        if (!MapUtil.isEmpty(headers)) {
            for ( Map.Entry<String, String> entry : headers.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return builder;
    }

    public String doPostJson(String url, String json) {
        logger.info("do post request and url [{}]", url);
        return executePost(url, json, JSON, null);
    }

    public String doPostJsonWithHeaders(String url, String json, Map<String, String> headers) {
        logger.info("do post request and url [{}]", url);
        return executePost(url, json, JSON, headers);
    }


    public String doPostXml(String url, String xml) {
        logger.info("do post request and url [{}]", url);
        return executePost(url, xml, XML, null);
    }

    public String executePost(String url, String data, MediaType contentType, Map<String, String> headers) {
        RequestBody requestBody = RequestBody.create(data.getBytes(StandardCharsets.UTF_8), contentType);
        Request.Builder builder = getBuilderWithHeaders(headers);
        Request request = builder.url(url).post(requestBody).build();

        return execute(request);
    }

    private String execute(Request request) {
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            logger.error(Throwables.getStackTraceAsString(e));
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return "";
    }

}
