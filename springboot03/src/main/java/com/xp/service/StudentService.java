package com.xp.service;

import com.xp.model.Student;
import java.util.List;

/**
 * 学生服务接口
 */
public interface StudentService {

  /**
   * 获取所有学生列表
   * 
   * @return 学生列表
   */
  List<Student> getAllStudents();

  /**
   * 根据学号获取学生
   * 
   * @param id 学生学号
   * @return 学生对象
   */
  Student getStudentById(String id);

  /**
   * 添加学生
   * 
   * @param student 学生对象
   * @return 添加后的学生对象
   */
  Student addStudent(Student student);

  /**
   * 更新学生信息
   * 
   * @param student 学生对象
   * @return 更新后的学生对象
   */
  Student updateStudent(Student student);

  /**
   * 删除学生
   * 
   * @param id 学生学号
   * @return 是否删除成功
   */
  boolean deleteStudent(String id);

  /**
   * 根据姓名或学号模糊查询学生
   * 
   * @param keyword 查询关键字
   * @return 符合条件的学生列表
   */
  List<Student> searchStudents(String keyword);
}