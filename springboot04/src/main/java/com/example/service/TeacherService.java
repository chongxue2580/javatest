package com.example.service;

import com.example.common.Result;
import com.example.entity.Teacher;
import java.util.List;

public interface TeacherService {
  Result<List<Teacher>> findAll();

  Result<Teacher> findById(Long id);

  Result<Teacher> save(Teacher teacher);

  Result<Teacher> update(Teacher teacher);

  Result<Void> deleteById(Long id);
}