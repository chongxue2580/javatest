package com.example.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Teacher {
  private Long id;
  private String teacherNo;
  private String name;
  private String title;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}