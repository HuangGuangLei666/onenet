package com.hgl.myforum.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HGL
 * @Date 2021/08/17
 */
@RestController
@RequestMapping(value = "/onenet")
@Slf4j
public class OneNetController {

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(HttpServletRequest request, HttpServletResponse response){
        log.info("OneNET开始推送数据>>>>>>");
        Map<String,Object> map = new HashMap<>();
        Map<String, String[]> maps = request.getParameterMap();
        for (String key : maps.keySet()) {
            System.out.print(key + ":");
            String[] values = maps.get(key);
            String valuess = "";
            for (String value : values) {
                System.out.println(value + "");
                valuess = value;
            }
            map.put(key, valuess);
        }
        System.out.println("map>>>>>>"+map);
        response.setStatus(200);
        return "";
    }

}
