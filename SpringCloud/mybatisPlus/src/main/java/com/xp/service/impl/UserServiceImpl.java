package com.xp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xp.entity.User;
import com.xp.mapper.UserMapper;
import com.xp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}