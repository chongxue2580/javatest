package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import java.util.List;

/**
 * 用户服务接口
 * 定义用户相关的业务操作
 */
public interface UserService {

  /**
   * 创建新用户
   * 
   * @param userDTO 用户信息
   * @return 创建成功的用户信息
   */
  UserDTO createUser(UserDTO userDTO);

  /**
   * 更新用户信息
   * 
   * @param id      用户ID
   * @param userDTO 更新的用户信息
   * @return 更新后的用户信息
   */
  UserDTO updateUser(Long id, UserDTO userDTO);

  /**
   * 获取用户信息
   * 
   * @param id 用户ID
   * @return 用户信息
   */
  UserDTO getUserById(Long id);

  /**
   * 获取所有用户列表
   * 
   * @return 用户列表
   */
  List<UserDTO> getAllUsers();

  /**
   * 删除用户
   * 
   * @param id 用户ID
   */
  void deleteUser(Long id);

  /**
   * 根据用户名查找用户
   * 
   * @param username 用户名
   * @return 用户信息
   */
  UserDTO findByUsername(String username);
}