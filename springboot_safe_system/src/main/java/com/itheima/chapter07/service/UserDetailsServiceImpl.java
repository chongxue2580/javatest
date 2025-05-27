package com.itheima.chapter07.service;

import com.itheima.chapter07.entity.UserDto;
import com.itheima.chapter07.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("正在尝试登录的用户名: " + username);
        
        // 根据用户名查询用户信息
        UserDto userDto = userMapper.getUserByUsername(username);
        if(userDto == null) {
            System.out.println("用户不存在: " + username);
            throw new UsernameNotFoundException("用户不存在");
        }
        
        System.out.println("找到用户: " + userDto.getUsername() + ", ID: " + userDto.getId());
        System.out.println("数据库中的密码: " + userDto.getPassword());
        
        // 查询用户权限
        List<String> privileges = userMapper.findPrivilegesByUserId(userDto.getId());
        System.out.println("用户权限: " + privileges);
        String[] privilegeArray = privileges.toArray(new String[0]);
        
        // 创建UserDetails对象
        return User.withUsername(userDto.getUsername())
                .password(userDto.getPassword())
                .authorities(privilegeArray)
                .disabled(userDto.getValid() != 1)
                .build();
    }
} 