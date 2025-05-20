package com.xp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 主控制器
 */
@Controller
public class MainController {

  /**
   * 首页重定向到学生列表
   */
  @GetMapping("/")
  public String index() {
    return "redirect:/student/list";
  }
}