package com.xp.controller;

import com.xp.model.Student;
import com.xp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 学生控制器
 */
@Controller
@RequestMapping("/student")
public class StudentController {

  @Autowired
  private StudentService studentService;

  /**
   * 显示学生列表页面
   */
  @GetMapping("/list")
  public String list(Model model, @RequestParam(required = false) String keyword) {
    model.addAttribute("students", studentService.searchStudents(keyword));
    model.addAttribute("keyword", keyword);
    return "student/list";
  }

  /**
   * 显示添加学生页面
   */
  @GetMapping("/add")
  public String showAddForm(Model model) {
    model.addAttribute("student", new Student());
    return "student/edit";
  }

  /**
   * 显示编辑学生页面
   */
  @GetMapping("/edit/{id}")
  public String showEditForm(@PathVariable String id, Model model) {
    Student student = studentService.getStudentById(id);
    model.addAttribute("student", student);
    return "student/edit";
  }

  /**
   * 保存学生信息（添加或更新）
   */
  @PostMapping("/save")
  public String saveStudent(Student student) {
    if (studentService.getStudentById(student.getId()) == null) {
      studentService.addStudent(student);
    } else {
      studentService.updateStudent(student);
    }
    return "redirect:/student/list";
  }

  /**
   * 删除学生
   */
  @GetMapping("/delete/{id}")
  public String deleteStudent(@PathVariable String id) {
    studentService.deleteStudent(id);
    return "redirect:/student/list";
  }

  /**
   * 首页重定向到学生列表
   */
  @GetMapping("/")
  public String index() {
    return "redirect:/student/list";
  }
}