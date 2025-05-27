package com.itheima.chapter07.controller;

import com.itheima.chapter07.entity.UserDto;
import com.itheima.chapter07.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/customLogin")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        System.out.println("自定义登录控制器: 用户名=" + username + ", 密码=" + password);
        
        try {
            // 创建认证令牌
            UsernamePasswordAuthenticationToken token = 
                new UsernamePasswordAuthenticationToken(username, password);
            
            // 进行认证
            Authentication authentication = authenticationManager.authenticate(token);
            
            // 认证成功，将认证信息存入SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            System.out.println("认证成功: " + authentication);
            
            // 查询用户信息并存入session
            UserDto userDto = userMapper.getUserByUsername(username);
            if (userDto != null) {
                session.setAttribute("user", userDto);
                System.out.println("用户信息已存入session: " + userDto.getUsername() + ", " + userDto.getName());
            }
            
            return "redirect:/";
        } catch (AuthenticationException e) {
            System.out.println("认证失败: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/loginview?error";
        }
    }
} 