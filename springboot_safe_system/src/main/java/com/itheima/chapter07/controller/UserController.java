package com.itheima.chapter07.controller;

import com.itheima.chapter07.entity.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    
    @ModelAttribute
    public void getUserInfo(HttpSession session) {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // 创建UserDto对象并设置到session中
            UserDto user = new UserDto();
            user.setUsername(authentication.getName());
            user.setName(authentication.getName()); // 这里可以根据实际情况设置显示名称
            session.setAttribute("user", user);
        }
    }
} 