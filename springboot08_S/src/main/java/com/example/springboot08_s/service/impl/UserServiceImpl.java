package com.example.springboot08_s.service.impl;

import com.example.springboot08_s.entity.SysUser;
import com.example.springboot08_s.mapper.SysUserMapper;
import com.example.springboot08_s.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

//@Service
public class UserServiceImpl implements UserService {

//    @Autowired
    private SysUserMapper userMapper;

    @Override
    public SysUser findByUsername(String username) {
        return null; //暂时返回null，因为我们使用内存认证
    }
} 