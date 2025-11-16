package com.java.controller;

import com.java.entity.User;
import com.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 1. 基本参数绑定 (@RequestParam) -> 返回 JSP 视图
     * 访问 URL: /user/getUser?id=1
     */
    @GetMapping("/getUser")
    public String getUserById(@RequestParam("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user); // 将数据放入 Model，以便在 JSP 中访问
        return "user-details"; // 返回逻辑视图名，将被解析为 /WEB-INF/views/user-details.jsp
    }

    /**
     * 2. 实体类参数绑定 -> 返回 JSON
     * 访问 URL: /user/addUser (方法: POST, Body: name=王五&age=35)
     */
    @PostMapping("/addUser")
    @ResponseBody
    public Map<String, Object> addUser(User user) {
        userService.addUser(user);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "用户 '" + user.getName() + "' 添加成功!");
        return response;
    }

    /**
     * 3. 路径参数绑定 (@PathVariable) -> 返回 JSON
     * 访问 URL: /user/get/1
     */
    @GetMapping("/get/{id}")
    @ResponseBody
    public User getUserByIdFromPath(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        return user;
    }
}