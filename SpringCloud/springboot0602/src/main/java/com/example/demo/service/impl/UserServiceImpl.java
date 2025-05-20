package com.example.demo.service.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务实现类
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDTO createUser(UserDTO userDTO) {
    // 检查用户名是否已存在
    if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
      throw new IllegalArgumentException("用户名已存在");
    }

    // DTO转换为实体
    User user = new User();
    BeanUtils.copyProperties(userDTO, user);

    // 保存用户
    user = userRepository.save(user);

    // 实体转换为DTO
    UserDTO savedUserDTO = new UserDTO();
    BeanUtils.copyProperties(user, savedUserDTO);
    return savedUserDTO;
  }

  @Override
  public UserDTO updateUser(Long id, UserDTO userDTO) {
    // 检查用户是否存在
    User user = userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("用户不存在"));

    // 如果修改了用户名，检查新用户名是否已存在
    if (!user.getUsername().equals(userDTO.getUsername()) &&
        userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
      throw new IllegalArgumentException("新用户名已存在");
    }

    // 更新用户信息
    BeanUtils.copyProperties(userDTO, user, "id");
    user = userRepository.save(user);

    // 转换为DTO返回
    UserDTO updatedUserDTO = new UserDTO();
    BeanUtils.copyProperties(user, updatedUserDTO);
    return updatedUserDTO;
  }

  @Override
  public UserDTO getUserById(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("用户不存在"));

    UserDTO userDTO = new UserDTO();
    BeanUtils.copyProperties(user, userDTO);
    return userDTO;
  }

  @Override
  public List<UserDTO> getAllUsers() {
    return userRepository.findAll().stream()
        .map(user -> {
          UserDTO userDTO = new UserDTO();
          BeanUtils.copyProperties(user, userDTO);
          return userDTO;
        })
        .collect(Collectors.toList());
  }

  @Override
  public void deleteUser(Long id) {
    if (!userRepository.existsById(id)) {
      throw new EntityNotFoundException("用户不存在");
    }
    userRepository.deleteById(id);
  }

  @Override
  public UserDTO findByUsername(String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new EntityNotFoundException("用户不存在"));

    UserDTO userDTO = new UserDTO();
    BeanUtils.copyProperties(user, userDTO);
    return userDTO;
  }
}