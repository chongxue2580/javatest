package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("student")
public class Student {
  @TableId(type = IdType.AUTO)
  private Long id;
  private String studentNo;
  private String name;
  private String gender;
  private String grade;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}