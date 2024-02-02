package com.hgg.upcmpt.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.hgg.upcmpt.config.OneNETConfig;
import com.hgg.upcmpt.onenet.response.ResultData;
import com.hgg.upcmpt.onenet.api.datastreams.FindDatastreamListApi;
import com.hgg.upcmpt.onenet.api.mqtt.SendMqttApi;
import com.hgg.upcmpt.onenet.response.BasicResponse;
import com.hgg.upcmpt.onenet.response.datastreams.DatastreamsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * @author HGL
 * @Date 2021/08/17
 */
@Slf4j
@RestController
@RequestMapping(value = "/OneNET")
public class OneNetController {

    @CrossOrigin
    @RequestMapping(value = "/getDataStreamList")
    @ResponseBody
    public ResultData getDataStreamList(){
        log.info("查询多个数据流开始>>>");
        FindDatastreamListApi api = new FindDatastreamListApi(OneNETConfig.dataStream, OneNETConfig.lowCmptDevId, OneNETConfig.apiKey);
        BasicResponse<List<DatastreamsResponse>> response = api.executeApi();
        Gson gson = new Gson();
        JSONObject jsonObject = gson.fromJson(response.getJson(), JSONObject.class);
        Object data = jsonObject.get("data");
        log.info("查询多个数据流结束<<<响应结果<<<response={}", gson.toJson(response));
        return ResultData.builder().code(0).msg("succ").data(data).build();
    }

    @CrossOrigin
    @RequestMapping(value = "/sendTopic")
    @ResponseBody
    public ResultData sendTopic(@RequestParam Map<String, Object> params){
        String cmd = params.get("cmd").toString();
        log.info("发布{}消息到topic开始>>>", cmd);
        SendMqttApi api = new SendMqttApi(OneNETConfig.topicParam, cmd, OneNETConfig.masterApiKey);
        BasicResponse<Void> response = api.executeApi();
        Gson gson = new Gson();
        log.info("发布{}消息到topic结束<<<响应结果<<<response={}", cmd, gson.toJson(response));
        return ResultData.builder().code(response.getErrno()).msg(response.getError()).build();
    }

}
