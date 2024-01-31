package com.hgl.myforum.mapper;


import com.hgl.myforum.entity.TReply;
import com.hgl.myforum.entity.dto.ReplyDto;

import java.util.List;

public interface TReplyMapper {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(TReply record);
//
//    int insertSelective(TReply record);
//
//    TReply selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(TReply record);
//
//    int updateByPrimaryKey(TReply record);

    List<ReplyDto> getReplyList();
}