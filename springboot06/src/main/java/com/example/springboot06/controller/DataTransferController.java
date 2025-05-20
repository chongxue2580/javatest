package com.example.springboot06.controller;

import com.example.springboot06.entity.User;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DataTransferController {

  // 1. 直接在方法形参中接收参数
  @GetMapping("/method1")
  public Map<String, Object> method1(String username, String password) {
    Map<String, Object> result = new HashMap<>();
    result.put("username", username);
    result.put("password", password);
    result.put("method", "直接在方法形参中接收参数");
    return result;
  }

  // 2. 通过实体类接收参数
  @PostMapping("/method2")
  public User method2(@RequestBody User user) {
    return user;
  }

  // 3. 通过HttpServletRequest接收参数
  @RequestMapping("/method3")
  public Map<String, Object> method3(HttpServletRequest request) {
    Map<String, Object> result = new HashMap<>();
    result.put("username", request.getParameter("username"));
    result.put("password", request.getParameter("password"));
    result.put("method", "通过HttpServletRequest接收参数");
    return result;
  }

  // 4. 通过@PathVariable获取REST风格参数
  @GetMapping("/method4/{id}")
  public Map<String, Object> method4(@PathVariable String id) {
    Map<String, Object> result = new HashMap<>();
    result.put("id", id);
    result.put("method", "通过@PathVariable获取REST风格参数");
    return result;
  }

  // 5. 通过@RequestParam绑定请求参数
  @GetMapping("/method5")
  public Map<String, Object> method5(@RequestParam("username") String name,
      @RequestParam("password") String pwd) {
    Map<String, Object> result = new HashMap<>();
    result.put("username", name);
    result.put("password", pwd);
    result.put("method", "通过@RequestParam绑定请求参数");
    return result;
  }

  // 6. 添加一个新的方法，演示组合使用
  @PostMapping("/method6/{id}")
  public Map<String, Object> method6(@PathVariable String id,
      @RequestBody User user,
      @RequestParam(required = false) String extra) {
    Map<String, Object> result = new HashMap<>();
    result.put("id", id);
    result.put("user", user);
    result.put("extra", extra);
    result.put("method", "组合使用多种参数接收方式");
    return result;
  }
}