package com.hgl.myforum.controller;

import com.hgl.myforum.entity.dto.TopicDto;
import com.hgl.myforum.service.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author HGL
 * @Date 2020/9/3
 */
@RestController
@RequestMapping(value = "/topic")
public class TopicController {

    @Autowired
    private ITopicService topicService;

    @RequestMapping(value = "/getTopicList")
    public List<TopicDto> getTopicList(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        return topicService.getTopicList();
    }

    /**
     * 导出帖子列表excel
     * @param response
     */
    @RequestMapping(value = "/exportExcel")
    public void exportExcel(HttpServletResponse response){
        topicService.exportExcel(response);
    }
}
