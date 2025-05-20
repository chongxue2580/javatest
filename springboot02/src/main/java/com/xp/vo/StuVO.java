package com.xp.vo;

public class StuVO {
  private Integer id;
  private String name;
  private Integer age;
  private String gender;
  private String grade; // 用于显示成绩等级

  public StuVO(Integer id, String name, Integer age, String gender, String grade) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.grade = grade;
  }

  // Getters and Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
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
}