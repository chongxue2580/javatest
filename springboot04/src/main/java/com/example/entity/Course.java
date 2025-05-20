package com.example.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Course {
  private Long id;
  private String courseNo;
  private String name;
  private Long teacherId;
  private Teacher teacher;
  private Integer credit;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}
