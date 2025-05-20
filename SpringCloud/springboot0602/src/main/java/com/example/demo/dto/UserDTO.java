package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * 用户数据传输对象
 * 用于前后端数据交互，避免直接暴露实体类
 */
@Data
public class UserDTO {

  private Long id;

  /**
   * 用户名验证：
   * 1. 不能为空
   * 2. 长度在3-20之间
   * 3. 只能包含字母、数字和下划线
   */
  @NotBlank(message = "用户名不能为空")
  @Size(min = 3, max = 20, message = "用户名长度必须在3-20之间")
  @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "用户名只能包含字母、数字和下划线")
  private String username;

  /**
   * 密码验证：
   * 1. 不能为空
   * 2. 长度在6-20之间
   * 3. 必须包含字母和数字
   */
  @NotBlank(message = "密码不能为空")
  @Size(min = 6, max = 20, message = "密码长度必须在6-20之间")
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "密码必须包含至少一个字母和一个数字")
  private String password;

  /**
   * 邮箱验证：
   * 1. 不能为空
   * 2. 必须是有效的邮箱格式
   */
  @NotBlank(message = "邮箱不能为空")
  @Email(message = "邮箱格式不正确")
  private String email;
}