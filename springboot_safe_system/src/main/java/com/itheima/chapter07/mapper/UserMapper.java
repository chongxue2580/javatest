package com.itheima.chapter07.mapper;

import com.itheima.chapter07.entity.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    
    // 根据用户名查询用户信息
    @Select("SELECT * FROM user WHERE username = #{username}")
    UserDto getUserByUsername(String username);
    
    // 根据用户ID查询权限
    @Select("SELECT p.authority FROM user u " +
            "JOIN user_priv up ON u.id = up.user_id " +
            "JOIN priv p ON up.priv_id = p.id " +
            "WHERE u.id = #{userId}")
    List<String> findPrivilegesByUserId(Integer userId);
} 