package com.example.service;

import com.example.common.Result;
import com.example.entity.Course;
import java.util.List;

public interface CourseService {
  Result<List<Course>> findAll();

  Result<Course> findById(Long id);

  Result<Course> save(Course course);

  Result<Course> update(Course course);

  Result<Void> deleteById(Long id);
}