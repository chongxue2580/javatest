package com.example.mapper;

import com.example.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface CourseMapper {
  List<Course> findAll();

  Course findById(Long id);

  Course findByCourseNo(String courseNo);

  int insert(Course course);

  int update(Course course);

  int deleteById(Long id);

  @Select("SELECT COUNT(*) FROM course WHERE teacher_id = #{teacherId}")
  int countByTeacherId(Long teacherId);
}