package com.example.controller;

import com.example.common.Result;
import com.example.entity.Course;
import com.example.entity.Teacher;
import com.example.service.CourseService;
import com.example.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
  private final CourseService courseService;
  private final TeacherService teacherService;

  public CourseController(CourseService courseService, TeacherService teacherService) {
    this.courseService = courseService;
    this.teacherService = teacherService;
  }

  @GetMapping("/list")
  public String list(Model model) {
    Result<List<Course>> courseResult = courseService.findAll();
    Result<List<Teacher>> teacherResult = teacherService.findAll();
    model.addAttribute("courses", courseResult.getData());
    model.addAttribute("teachers", teacherResult.getData());
    model.addAttribute("active", "course");
    return "course/list";
  }

  @PostMapping("/add")
  @ResponseBody
  public Result<Course> add(@RequestBody Course course) {
    return courseService.save(course);
  }

  @GetMapping("/get/{id}")
  @ResponseBody
  public Result<Course> getById(@PathVariable Long id) {
    return courseService.findById(id);
  }

  @PostMapping("/update")
  @ResponseBody
  public Result<Course> update(@RequestBody Course course) {
    return courseService.update(course);
  }

  @PostMapping("/delete/{id}")
  @ResponseBody
  public Result<Void> delete(@PathVariable Long id) {
    return courseService.deleteById(id);
  }
}