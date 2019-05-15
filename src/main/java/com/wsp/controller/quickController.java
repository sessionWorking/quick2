package com.wsp.controller;

import com.wsp.mapper.UserMapper;
import com.wsp.pojo.User;
import com.wsp.pojo.UserExample;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class quickController {

    @Resource
    private UserMapper userMapper;
    @RequestMapping("/queryList")

    public List<User> QueryList(){
        System.out.println("oooo");
        return userMapper.selectByExample(new UserExample());

    }

}
