package com.hgl.myforum.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author HGL
 * @Date 2020/9/3
 */
@Data
public class TopicDto {

    private Integer id;

    private String title;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;

    private String userName;

}
