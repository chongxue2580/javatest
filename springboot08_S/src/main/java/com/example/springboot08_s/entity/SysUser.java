package com.example.springboot08_s.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SysUser {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private Boolean enabled;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
} 