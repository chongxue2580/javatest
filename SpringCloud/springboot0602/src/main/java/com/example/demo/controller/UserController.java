package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户管理RESTful API控制器
 */
@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * 创建用户
   * POST /api/v1/users
   * 
   * @param userDTO 用户信息
   * @return 创建成功的用户信息
   */
  @PostMapping
  public ResponseEntity<Result<UserDTO>> createUser(@Valid @RequestBody UserDTO userDTO) {
    UserDTO createdUser = userService.createUser(userDTO);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(Result.success(createdUser));
  }

  /**
   * 更新用户信息
   * PUT /api/v1/users/{id}
   * 
   * @param id      用户ID
   * @param userDTO 更新的用户信息
   * @return 更新后的用户信息
   */
  @PutMapping("/{id}")
  public ResponseEntity<Result<UserDTO>> updateUser(
      @PathVariable Long id,
      @Valid @RequestBody UserDTO userDTO) {
    UserDTO updatedUser = userService.updateUser(id, userDTO);
    return ResponseEntity.ok(Result.success(updatedUser));
  }

  /**
   * 获取用户信息
   * GET /api/v1/users/{id}
   * 
   * @param id 用户ID
   * @return 用户信息
   */
  @GetMapping("/{id}")
  public ResponseEntity<Result<UserDTO>> getUser(@PathVariable Long id) {
    UserDTO user = userService.getUserById(id);
    return ResponseEntity.ok(Result.success(user));
  }

  /**
   * 获取所有用户列表
   * GET /api/v1/users
   * 
   * @return 用户列表
   */
  @GetMapping
  public ResponseEntity<Result<List<UserDTO>>> getAllUsers() {
    List<UserDTO> users = userService.getAllUsers();
    return ResponseEntity.ok(Result.success(users));
  }

  /**
   * 删除用户
   * DELETE /api/v1/users/{id}
   * 
   * @param id 用户ID
   * @return 无内容
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * 根据用户名查找用户
   * GET /api/v1/users/username/{username}
   * 
   * @param username 用户名
   * @return 用户信息
   */
  @GetMapping("/username/{username}")
  public ResponseEntity<Result<UserDTO>> getUserByUsername(@PathVariable String username) {
    UserDTO user = userService.findByUsername(username);
    return ResponseEntity.ok(Result.success(user));
  }
}