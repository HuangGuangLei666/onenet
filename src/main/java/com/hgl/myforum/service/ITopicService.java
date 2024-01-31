package com.hgl.myforum.service;

import com.hgl.myforum.entity.dto.TopicDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author HGL
 * @Date 2020/9/2
 */
public interface ITopicService {

    List<TopicDto> getTopicList();

    void exportExcel(HttpServletResponse response);

    void regularPosting() throws Exception;
}
