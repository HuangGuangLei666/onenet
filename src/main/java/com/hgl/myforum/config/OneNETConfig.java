package com.hgl.myforum.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OneNETConfig {
    /**
     * 请求onenet地址
     */
    public static String url;
    /**
     * 数据流名称，多个用逗号隔开
     */
    public static String dataStream;
    /**
     * 下位机设备id
     */
    public static String lowCmptDevId;
    /**
     * APIKey的值
     */
    public static String apiKey;
    /**
     * topic参数值
     */
    public static String topicParam;
    /**
     * Master-APIkey值
     */
    public static String masterApiKey;

    @Value("${onenet.url}")
    public void setUrl(String url) {
        OneNETConfig.url = url;
    }

    @Value("${onenet.dataStream}")
    public void setDataStream(String dataStream) {
        OneNETConfig.dataStream = dataStream;
    }

    @Value("${onenet.lowCmptDevId}")
    public void setLowCmptDevId(String lowCmptDevId) {
        OneNETConfig.lowCmptDevId = lowCmptDevId;
    }

    @Value("${onenet.apiKey}")
    public void setApiKey(String apiKey) {
        OneNETConfig.apiKey = apiKey;
    }

    @Value("${onenet.topicParam}")
    public void setTopicParam(String topicParam) {
        OneNETConfig.topicParam = topicParam;
    }

    @Value("${onenet.masterApiKey}")
    public void setMasterApiKey(String masterApiKey) {
        OneNETConfig.masterApiKey = masterApiKey;
    }
}
