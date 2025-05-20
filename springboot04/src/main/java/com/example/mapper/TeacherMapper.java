package com.example.mapper;

import com.example.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface TeacherMapper {
  List<Teacher> findAll();

  Teacher findById(Long id);

  Teacher findByTeacherNo(String teacherNo);

  int insert(Teacher teacher);

  int update(Teacher teacher);

  int deleteById(Long id);
}