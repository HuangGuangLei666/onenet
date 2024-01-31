package com.hgl.myforum.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TReply {
    private Integer id;

    private String content;

    private Date createTime;

    private Integer topicId;

    private Integer userId;

}