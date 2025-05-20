package com.example.controller;

import com.example.common.Result;
import com.example.entity.Teacher;
import com.example.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

  private final TeacherService teacherService;

  public TeacherController(TeacherService teacherService) {
    this.teacherService = teacherService;
  }

  @GetMapping("/list")
  public String list(Model model) {
    Result<List<Teacher>> result = teacherService.findAll();
    model.addAttribute("teachers", result.getData());
    model.addAttribute("active", "teacher");
    return "teacher/list";
  }

  @PostMapping("/add")
  @ResponseBody
  public Result<Teacher> add(@RequestBody Teacher teacher) {
    return teacherService.save(teacher);
  }

  @PostMapping("/delete/{id}")
  @ResponseBody
  public Result<Void> delete(@PathVariable Long id) {
    return teacherService.deleteById(id);
  }

  @GetMapping("/get/{id}")
  @ResponseBody
  public Result<Teacher> getById(@PathVariable Long id) {
    return teacherService.findById(id);
  }

  @PostMapping("/update")
  @ResponseBody
  public Result<Teacher> update(@RequestBody Teacher teacher) {
    return teacherService.update(teacher);
  }
}