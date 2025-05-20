package com.example.springboot08_s.service;

import com.example.springboot08_s.entity.SysUser;

public interface UserService {
    SysUser findByUsername(String username);
} 