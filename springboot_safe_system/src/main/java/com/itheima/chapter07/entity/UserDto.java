package com.itheima.chapter07.entity;

public class UserDto {
    private Integer id;          // 用户编号
    private String username;     // 用户名称
    private String password;     // 密码
    private Integer valid;       // 是否有效
    private String name;         // 显示名称
    
    // setter/getter方法
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Integer getValid() {
        return valid;
    }
    
    public void setValid(Integer valid) {
        this.valid = valid;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
} 