package com.hgl.myforum.service;

import com.hgl.myforum.common.ResultResp;
import com.hgl.myforum.entity.TUser;

import java.util.List;

/**
 * @author HGL
 * @Date 2020/9/2
 */
public interface IUserService {
    List<TUser> getUserList();

    ResultResp delUserById(Integer userId);

    ResultResp register(String username, String password);
}
