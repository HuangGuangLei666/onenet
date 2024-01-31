package com.hgl.myforum.controller;

import com.hgl.myforum.entity.dto.ReplyDto;
import com.hgl.myforum.entity.dto.TopicDto;
import com.hgl.myforum.service.IReplyService;
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
@RequestMapping(value = "/reply")
public class ReplyController {

    @Autowired
    private IReplyService replyService;

    /**
     * 获取评论列表
     * @param response
     * @return
     */
    @RequestMapping(value = "/getReplyList")
    public List<ReplyDto> getReplyList(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        return replyService.getReplyList();
    }
}
