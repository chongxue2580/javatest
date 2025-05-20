package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * 用户数据访问接口
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * 根据用户名查找用户
   * 
   * @param username 用户名
   * @return 用户信息
   */
  Optional<User> findByUsername(String username);

  /**
   * 根据邮箱查找用户
   * 
   * @param email 邮箱
   * @return 用户信息
   */
  Optional<User> findByEmail(String email);
}