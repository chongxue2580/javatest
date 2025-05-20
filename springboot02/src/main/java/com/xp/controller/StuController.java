package com.xp.controller;

import com.xp.pojo.Stu;
import com.xp.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StuController {

    @Autowired
    private StuService stuService;

    @GetMapping("/listStu")
    public List<Stu> list() {
        return stuService.listStu();
    }
}

// 新建内部类用于返回带等级的学生信息
class StuGrade {
    private int id;
    private String name;
    private String grade;

    public StuGrade(int id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
