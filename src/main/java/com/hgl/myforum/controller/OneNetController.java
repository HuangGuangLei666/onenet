package com.hgl.myforum.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.hgl.myforum.onenet.ResultData;
import com.hgl.myforum.onenet.api.datastreams.FindDatastreamListApi;
import com.hgl.myforum.onenet.api.mqtt.SendMqttApi;
import com.hgl.myforum.onenet.response.BasicResponse;
import com.hgl.myforum.onenet.response.datastreams.DatastreamsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * @author HGL
 * @Date 2021/08/17
 */
@RestController
@RequestMapping(value = "/OneNET")
@Slf4j
public class OneNetController {

    @CrossOrigin
    @RequestMapping(value = "/getDataStreamList")
    @ResponseBody
    public ResultData getDataStreamList(){
        log.info("查询多个数据流开始>>>>>>");
        String datastreamids = "wendu,shidu,guang,led,fan,heating,curtain";
        String devid = "1185537350";
        String key = "soRab9XyC82UlDatDB7W2iF=E78=";
        FindDatastreamListApi api = new FindDatastreamListApi(datastreamids, devid, key);
        BasicResponse<List<DatastreamsResponse>> response = api.executeApi();
        log.info("查询多个数据流结束<<<<<<");

        Gson gson = new Gson();
        JSONObject jsonObject = gson.fromJson(response.getJson(), JSONObject.class);
        Object data = jsonObject.get("data");
        System.out.println(data);

        ResultData resultData = new ResultData();
        resultData.setCode(0);
        resultData.setMsg("成功！！！");
        resultData.setData(data);

        return resultData;
    }

    @CrossOrigin
    @RequestMapping(value = "/sendTopic")
    @ResponseBody
    public ResultData sendTopic(@RequestParam Map<String, Object> params){
        log.info("发送Topic命令开始>>>>>>");
        String topic="web";
        String key="VGnkw9CtI60y2PjtA7AxjP8Cf1A=";
        String cmd = params.get("cmd").toString();
        System.out.println("@@@@@@@@@@"+cmd);
        SendMqttApi api = new SendMqttApi(topic, cmd, key);
        BasicResponse<Void> response = api.executeApi();
        System.out.println("errno:"+response.errno+" error:"+response.error);
        log.info("发送Topic命令结束<<<<<<");

        ResultData resultData = new ResultData();
        resultData.setCode(0);
        resultData.setMsg("成功！！！");

        return resultData;
    }

}
