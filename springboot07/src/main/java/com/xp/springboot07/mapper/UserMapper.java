package com.xp.springboot07.mapper;

import com.xp.springboot07.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    
    @Select("SELECT * FROM user")
    List<User> findAll();
    
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(@Param("id") Long id);
    
    @Insert("INSERT INTO user(username, email, age, address) VALUES(#{username}, #{email}, #{age}, #{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);
    
    @Update("UPDATE user SET username = #{username}, email = #{email}, age = #{age}, address = #{address} WHERE id = #{id}")
    int update(User user);
    
    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
} 