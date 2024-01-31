package com.hgl.myforum.service.impl;

import com.hgl.myforum.entity.dto.ReplyDto;
import com.hgl.myforum.mapper.TReplyMapper;
import com.hgl.myforum.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HGL
 * @Date 2020/9/2
 */
@Service
public class ReplyServiceImpl implements IReplyService {

    @Autowired
    private TReplyMapper replyMapper;

    public List<ReplyDto> getReplyList() {
        return replyMapper.getReplyList();
    }
}
