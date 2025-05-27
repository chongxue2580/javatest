package com.itheima.chapter07.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // 数据库中存储的密码
        String dbPassword = "$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW";
        
        // 测试密码是否匹配
        boolean isMatch = encoder.matches("1234", dbPassword);
        System.out.println("密码匹配结果: " + isMatch);
        
        // 生成新的加密密码
        String newPassword = encoder.encode("1234");
        System.out.println("新生成的密码: " + newPassword);
        
        // 测试新生成的密码是否匹配
        boolean isNewMatch = encoder.matches("1234", newPassword);
        System.out.println("新密码匹配结果: " + isNewMatch);
    }
} 