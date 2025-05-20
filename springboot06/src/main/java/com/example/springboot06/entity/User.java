package com.example.springboot06.entity;

public class User {
  private String username;
  private String password;
  private Integer age;

  public User() {
  }

  public User(String username, String password, Integer age) {
    this.username = username;
    this.password = password;
    this.age = age;
  }

  // Getters and Setters
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}