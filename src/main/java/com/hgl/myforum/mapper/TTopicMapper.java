package com.hgl.myforum.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hgl.myforum.entity.TTopic;
import com.hgl.myforum.entity.dto.TopicDto;

import java.util.List;

public interface TTopicMapper extends BaseMapper<TTopic> {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(TTopic record);
//
//    int insertSelective(TTopic record);
//
//    TTopic selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(TTopic record);
//
//    int updateByPrimaryKey(TTopic record);
//
    List<TopicDto> getTopicList();
}