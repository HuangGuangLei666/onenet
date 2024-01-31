package com.hgl.myforum.controller;

import com.hgl.myforum.common.EnumResp;
import com.hgl.myforum.common.ResultResp;
import com.hgl.myforum.entity.TUser;
import com.hgl.myforum.service.IUserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author HGL
 * @Date 2021/08/17
 */
@RestController
@RequestMapping(value = "/user")
@Log
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 获取用户列表
     * @param response
     * @return
     */
    @RequestMapping(value = "/getUserList")
    public List<TUser> getUserList(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        return userService.getUserList();
    }

    @RequestMapping(value = "/delUserById")
    public ResultResp delUserById(Integer userId,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        return userService.delUserById(userId);
    }

    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/register")
    public ResultResp register(String username,String password){
        ResultResp resp = new ResultResp();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            resp.setCode(EnumResp.USNAMEPWDNOTNULL.getCode());
            resp.setDesc(EnumResp.USNAMEPWDNOTNULL.getDesc());
            return resp;
        }
        return userService.register(username,password);
    }

}
