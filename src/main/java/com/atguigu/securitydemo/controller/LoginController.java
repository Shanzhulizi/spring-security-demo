package com.atguigu.securitydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    /**
     * 这里的方法是调用resources/templates/login.html
     * @return
     */
    @GetMapping("/login")
    public String login(){//希望方法找到一个视图解析器

        return "";
    }
}
