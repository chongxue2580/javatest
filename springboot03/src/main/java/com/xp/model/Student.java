package com.xp.model;

import java.io.Serializable;

/**
 * 学生实体类
 */
public class Student implements Serializable {
  private String id; // 学号
  private String name; // 姓名
  private int age; // 年龄
  private String gender; // 性别
  private String grade; // 年级
  private int score; // 成绩

  public Student() {
  }

  public Student(String id, String name, int age, String gender, String grade, int score) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.grade = grade;
    this.score = score;
  }

  // 判断是否及格
  public boolean isPassed() {
    return this.score >= 60;
  }

  // 获取等级
  public String getLevel() {
    if (score >= 80) {
      return "良好";
    } else if (score >= 60) {
      return "及格";
    } else {
      return "不及格";
    }
  }

  // getter和setter
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", age=" + age +
        ", gender='" + gender + '\'' +
        ", grade='" + grade + '\'' +
        ", score=" + score +
        '}';
  }
}