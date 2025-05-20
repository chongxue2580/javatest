package com.xp.springboot07.controller;

import com.xp.springboot07.common.Result;
import com.xp.springboot07.entity.User;
import com.xp.springboot07.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理控制器
 * RESTful API 实现
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin  // 允许跨域请求
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户列表
     * @return
     */
    @GetMapping
    public Result<List<User>> getAllUsers() {
        return Result.success(userService.findAll());
    }

    /**
     * 根据ID获取指定用户信息
     * @param id 用户ID
     * @return
     */
    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        return user != null ? Result.success(user) : Result.error("未找到该用户");
    }

    /**
     * 创建新用户
     * @param user 用户信息（不包含ID）
     * @return
     */
    @PostMapping
    public Result<User> createUser(@RequestBody User user) {
        try {
            return Result.success(userService.create(user));
        } catch (Exception e) {
            return Result.error("创建用户失败：" + e.getMessage());
        }
    }

    /**
     * 更新用户信息
     * @param id 用户ID
     * @param user 更新后的用户信息
     * @return
     */
    @PutMapping("/{id}")
    public Result<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        try {
            User updatedUser = userService.update(user);
            return updatedUser != null ? Result.success(updatedUser) : Result.error("未找到该用户");
        } catch (Exception e) {
            return Result.error("更新用户信息失败：" + e.getMessage());
        }
    }

    /**
     * 删除用户
     * @param id 要删除的用户ID
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteUser(@PathVariable Long id) {
        try {
            boolean deleted = userService.delete(id);
            return deleted ? Result.success(true) : Result.error("未找到该用户");
        } catch (Exception e) {
            return Result.error("删除用户失败：" + e.getMessage());
        }
    }
} 