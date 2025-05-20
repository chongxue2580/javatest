package com.example.springboot0601.Controller;

import com.example.springboot0601.entity.User;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class UserController {

    // 1. 直接将参数写在方法形参中，支持GET和POST
    @RequestMapping(value = "/method1", method = {RequestMethod.GET, RequestMethod.POST})
    public String method1(String name, Integer age) {
        return "Method1 - Name: " + name + ", Age: " + age;
    }

    // 2. 将参数封装在实体类中，支持GET和POST
    @RequestMapping(value = "/method2", method = {RequestMethod.GET, RequestMethod.POST})
    public String method2(@ModelAttribute User user) {
        return "Method2 - Name: " + user.getName() + ", Age: " + user.getAge();
    }

    // 3. 通过HttpServletRequest接收，支持GET和POST
    @RequestMapping(value = "/method3", method = {RequestMethod.GET, RequestMethod.POST})
    public String method3(HttpServletRequest request) {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        return "Method3 - Name: " + name + ", Age: " + age;
    }

    // 4. 通过@PathVariable获取REST风格路径参数
    @GetMapping("/method4/{name}/{age}")
    public String method4(@PathVariable String name, @PathVariable Integer age) {
        return "Method4 - Name: " + name + ", Age: " + age;
    }

    // 5. 用@RequestParam绑定请求参数，支持GET和POST
    @RequestMapping(value = "/method5", method = {RequestMethod.GET, RequestMethod.POST})
    public String method5(@RequestParam("name") String userName, @RequestParam("age") Integer userAge) {
        return "Method5 - Name: " + userName + ", Age: " + userAge;
    }

    // 6. 用@RequestBody绑定请求参数（通常用于POST）
    @PostMapping("/method6")
    public String method6(@RequestBody User user) {
        return "Method6 - Name: " + user.getName() + ", Age: " + user.getAge();
    }
}

