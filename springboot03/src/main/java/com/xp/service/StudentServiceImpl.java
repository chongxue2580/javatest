package com.xp.service;

import com.xp.model.Student;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 学生服务实现类
 */
@Service
public class StudentServiceImpl implements StudentService {

  // 使用Map存储学生数据，key为学号
  private Map<String, Student> studentMap = new HashMap<>();

  /**
   * 初始化一些测试数据
   */
  @PostConstruct
  public void init() {
    // 添加10个测试学生，学号2001开始按顺序排列，姓名使用姓+名的格式
    addStudent(new Student("2001", "张明", 21, "男", "2023级", 67));
    addStudent(new Student("2002", "李华", 20, "男", "2023级", 89));
    addStudent(new Student("2003", "王军", 21, "男", "2023级", 59));
    addStudent(new Student("2004", "赵燕", 20, "女", "2023级", 91));
    addStudent(new Student("2005", "刘芳", 18, "女", "2023级", 77));
    addStudent(new Student("2006", "陈强", 21, "男", "2023级", 45));
    addStudent(new Student("2007", "吴刚", 18, "男", "2023级", 65));
    addStudent(new Student("2008", "孙伟", 18, "男", "2023级", 80));
    addStudent(new Student("2009", "周丽", 19, "女", "2023级", 73));
    addStudent(new Student("2010", "徐静", 18, "女", "2023级", 85));
  }

  @Override
  public List<Student> getAllStudents() {
    return new ArrayList<>(studentMap.values());
  }

  @Override
  public Student getStudentById(String id) {
    return studentMap.get(id);
  }

  @Override
  public Student addStudent(Student student) {
    studentMap.put(student.getId(), student);
    return student;
  }

  @Override
  public Student updateStudent(Student student) {
    if (studentMap.containsKey(student.getId())) {
      studentMap.put(student.getId(), student);
      return student;
    }
    return null;
  }

  @Override
  public boolean deleteStudent(String id) {
    if (studentMap.containsKey(id)) {
      studentMap.remove(id);
      return true;
    }
    return false;
  }

  @Override
  public List<Student> searchStudents(String keyword) {
    if (keyword == null || keyword.trim().isEmpty()) {
      return getAllStudents();
    }

    String searchKey = keyword.toLowerCase();
    return studentMap.values().stream()
        .filter(student -> student.getId().toLowerCase().contains(searchKey) ||
            student.getName().toLowerCase().contains(searchKey))
        .collect(Collectors.toList());
  }
}