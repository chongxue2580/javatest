package com.itheima.chapter07.entity;

public class Privilege {
    private Integer id;          // 编号
    private String authority;    // 权限
    
    // setter/getter方法
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getAuthority() {
        return authority;
    }
    
    public void setAuthority(String authority) {
        this.authority = authority;
    }
} 