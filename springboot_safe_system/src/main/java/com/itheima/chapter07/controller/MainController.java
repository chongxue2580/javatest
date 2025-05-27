package com.itheima.chapter07.controller;

import com.itheima.chapter07.entity.UserDto;
import com.itheima.chapter07.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
public class MainController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String home(HttpSession session) {
        // 获取当前登录用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
            String username = authentication.getName();
            System.out.println("主页面加载，当前用户: " + username);
            
            // 查询用户信息并存入session
            UserDto userDto = userMapper.getUserByUsername(username);
            if (userDto != null) {
                session.setAttribute("user", userDto);
                System.out.println("用户信息已存入session: " + userDto.getUsername() + ", " + userDto.getName());
            }
        }
        return "main";
    }

    @GetMapping("/loginview")
    public String login() {
        return "login";
    }
} 