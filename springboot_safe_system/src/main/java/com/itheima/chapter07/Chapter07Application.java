package com.itheima.chapter07;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@MapperScan("com.itheima.chapter07.mapper")
public class Chapter07Application {
    public static void main(String[] args) {
        // 测试密码匹配
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String dbPassword = "$2a$10$ahsaCDzyL01Fn4LpRRoRzOu0JrG6YAY43Rf0dlQY0Yd60US3r485W";
        boolean matches = encoder.matches("1234", dbPassword);
        System.out.println("密码1234匹配结果: " + matches);
        
        // 生成新的加密密码
        String newEncryptedPassword = encoder.encode("1234");
        System.out.println("新生成的加密密码: " + newEncryptedPassword);
        System.out.println("新密码匹配结果: " + encoder.matches("1234", newEncryptedPassword));
        
        // 启动应用
        SpringApplication.run(Chapter07Application.class, args);
    }
} 