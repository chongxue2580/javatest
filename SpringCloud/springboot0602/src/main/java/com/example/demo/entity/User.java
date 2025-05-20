package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 用户实体类
 * 
 * @author demo
 */
@Data
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

  /**
   * 用户ID - 主键，自动生成
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 用户名 - 不能为空，长度在3-20之间
   */
  @Column(nullable = false, length = 20, unique = true)
  private String username;

  /**
   * 密码 - 不能为空，长度在6-100之间
   */
  @Column(nullable = false, length = 100)
  private String password;

  /**
   * 邮箱 - 唯一约束
   */
  @Column(unique = true)
  private String email;

  /**
   * 创建时间 - 自动设置
   */
  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  /**
   * 更新时间 - 自动更新
   */
  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}