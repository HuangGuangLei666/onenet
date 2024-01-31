package com.hgl.myforum.service;

import com.hgl.myforum.entity.dto.ReplyDto;
import com.hgl.myforum.entity.dto.TopicDto;

import java.util.List;

/**
 * @author HGL
 * @Date 2020/9/2
 */
public interface IReplyService {

    List<ReplyDto> getReplyList();
}
