package com.example.controller;

import com.example.common.Result;
import com.example.entity.Student;
import com.example.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping("/list")
  public String list(Model model) {
    Result<List<Student>> result = studentService.findAll();
    model.addAttribute("students", result.getData());
    model.addAttribute("active", "student");
    return "student/list";
  }

  @PostMapping("/add")
  @ResponseBody
  public Result<Student> add(@RequestBody Student student) {
    return studentService.save(student);
  }

  @GetMapping("/get/{id}")
  @ResponseBody
  public Result<Student> getById(@PathVariable Long id) {
    return studentService.findById(id);
  }

  @PostMapping("/update")
  @ResponseBody
  public Result<Student> update(@RequestBody Student student) {
    return studentService.update(student);
  }

  @PostMapping("/delete/{id}")
  @ResponseBody
  public Result<Void> delete(@PathVariable Long id) {
    return studentService.deleteById(id);
  }
}