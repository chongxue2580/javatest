package com.example.service;

import com.example.common.Result;
import com.example.entity.Student;
import java.util.List;

public interface StudentService {
  Result<List<Student>> findAll();

  Result<Student> findById(Long id);

  Result<Student> save(Student student);

  Result<Student> update(Student student);

  Result<Void> deleteById(Long id);
}