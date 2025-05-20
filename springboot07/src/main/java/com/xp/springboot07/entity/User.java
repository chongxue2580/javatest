package com.xp.springboot07.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String email;
    private Integer age;
    private String address;
} 